package controller;

import com.google.gson.JsonObject;
import database.CustomerDAO;
import database.OrderDAO;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String action = request.getParameter("action");

        action = action.toUpperCase();
        switch (action) {
            case "INIT" : {
                //        top product
                Map<ProductUnit,Integer> topPro= new LinkedHashMap<>();
                topPro = OrderDAO.getInstance().selectTopProductUnit(2,10);
                Map<User,Integer> topCus= new LinkedHashMap<>();
                topCus = OrderDAO.getInstance().selectTopCustomer(2,10);

//        sales
                int sales = OrderDAO.getInstance().countOrder(1);
                int presales = OrderDAO.getInstance().countPreOrder(1);
                if(presales ==0){
                    presales=1;
                }
                double salesGap = (double) sales / (double) presales * 100;
                salesGap = (double) Math.round(salesGap*100) /100;


//        new customer
                int customer = OrderDAO.getInstance().selectCusAmount(3);
                int precustomer = OrderDAO.getInstance().selectPreCusAmount(3);
                if(precustomer ==0){
                    precustomer=1;
                }
                double customerGap = (double) customer / (double) precustomer * 100;
                customerGap = (double) Math.round(customerGap*100) /100;


//        revenue
                int revenue = OrderDAO.getInstance().selectRevenue(2);
                int preRevenue = OrderDAO.getInstance().selectPreRevenue(2);
                if(preRevenue ==0){
                    preRevenue=1;
                }
                double revenueGap = (double) revenue / (double) preRevenue * 100;
                revenueGap = (double) Math.round(revenueGap*100) /100;

//        report
                ArrayList<Integer> periods = new ArrayList<>();
                ArrayList<Integer> salesReport = new ArrayList<>();
                ArrayList<Integer> revenueReport = new ArrayList<>();
                ArrayList<Integer> customerReport = new ArrayList<>();

                ArrayList<String> datetime = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();

//                        cac moc nam can lay du lieu
                for(int i=2020; i<=calendar.get(Calendar.YEAR);i++) {
                    periods.add(i);
                    datetime.add(String.valueOf(i));
                }

                salesReport = OrderDAO.getInstance().selectReportSales(4,periods);
                revenueReport = OrderDAO.getInstance().selectReportRevenue(4,periods);
                customerReport = OrderDAO.getInstance().selectReportCustomer(4,periods);

//                System.out.println("salesreport: " + salesReport.size());
//                System.out.println("revenueREportt: "+revenueReport.size());
//                System.out.println("customer report: "+ customerReport.size());
//                System.out.println("period size: " + periods.size());

                int salesArr[] = new int[periods.size()];
                int revenueArr[] = new int[periods.size()];
                int customerArr[] = new int[periods.size()];
                String datetimeArr[] = new String[periods.size()];

                for(int i=0;i<datetime.size();i++) {
                    datetimeArr[i] = datetime.get(i);
                }

                int j =0;

                for(int i=0;i<(periods.size());i+=2) {
                    if(periods.contains(salesReport.get(i))) salesArr[j] = salesReport.get(i+1);
                    if(periods.contains(revenueReport.get(i))) revenueArr[j] = revenueReport.get(i+1);
                    if(periods.contains(customerReport.get(i))) customerArr[j] = customerReport.get(i+1);

                    j++;

                }


                JsonObject json = new JsonObject();
                json.addProperty("doanhSo", Arrays.toString(salesArr));
                json.addProperty("doanhThu", Arrays.toString(revenueArr));
                json.addProperty("khachHang", Arrays.toString(customerArr));
                json.addProperty("categories", Arrays.toString(datetimeArr));


//        recent customer
                Map<User, Order> recentCustomer = CustomerDAO.getInstance().getRecentCustomer(5);

//        unback customer
                ArrayList<User> unbackCustomer = CustomerDAO.getInstance().getUnbackCus(3,0,10);

                request.setAttribute("topPro", topPro);
                request.setAttribute("topCus", topCus);
                request.setAttribute("sales", sales);
                request.setAttribute("salesGap", salesGap);
                request.setAttribute("customer", customer);
                request.setAttribute("customerGap", customerGap);
                request.setAttribute("revenue", revenue);
                request.setAttribute("revenueGap", revenueGap);
                request.setAttribute("reportData", json.toString());
                request.setAttribute("recentCustomer", recentCustomer);
                request.setAttribute("unbackCustomer", unbackCustomer);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminDashboard.jsp");
                rd.forward(request, response);
                break;
            }
            case "SALES" : {
                int timee = Integer.parseInt(request.getParameter("timee"));
                int sales = OrderDAO.getInstance().countOrder(timee);
                int presales = OrderDAO.getInstance().countPreOrder(timee);
                if(presales ==0){
                    presales=1;
                }
                double salesGap = (double) sales / (double) presales * 100;
                salesGap = (double) Math.round(salesGap*100) /100;

                String html = renderInfoHTML(sales,salesGap);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }
            case "TOPPRO": {
                int timee = Integer.parseInt(request.getParameter("timee"));
//                int top = Integer.parseInt(request.getParameter("top"));
                int top=10;
                Map<ProductUnit,Integer> topPro= new LinkedHashMap<>();
                topPro = OrderDAO.getInstance().selectTopProductUnit(timee,top);

                String html = renderTopProHTML(topPro);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }

            case "REVENUE": {
                int timee = Integer.parseInt(request.getParameter("timee"));
                int revenue = OrderDAO.getInstance().selectRevenue(timee);
                int preRevenue = OrderDAO.getInstance().selectPreRevenue(timee);
                if(preRevenue ==0){
                    preRevenue=1;
                }
                double revenueGap = (double) revenue / (double) preRevenue * 100;
                revenueGap = (double) Math.round(revenueGap*100) /100;

                String html = renderInfoHTML(revenue,revenueGap);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }

            case "CUSTOMER": {
                int timee = Integer.parseInt(request.getParameter("timee"));
                int cus = OrderDAO.getInstance().selectCusAmount(timee);
                int preCus = OrderDAO.getInstance().selectPreCusAmount(timee);
                if(preCus ==0){
                    preCus=1;
                }
                double cusGap = (double) cus / (double) preCus * 100;
                cusGap = (double) Math.round(cusGap*100) /100;

                String html = renderInfoHTML(cus,cusGap);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }

            case "REPORT": {
                int timee = Integer.parseInt(request.getParameter("timee"));
                ArrayList<Integer> periods = new ArrayList<>();
                ArrayList<Integer> sales = new ArrayList<>();
                ArrayList<Integer> revenue = new ArrayList<>();
                ArrayList<Integer> customer = new ArrayList<>();

                ArrayList<String> datetime = new ArrayList<>();
                Calendar calendar = Calendar.getInstance();

                switch (timee) {
                    case 1: {
//                    cac moc gio can lay du lieu
                        periods.add(0);
                        periods.add(2);
                        periods.add(9);
                        periods.add(15);
                        periods.add(18);
                        periods.add(21);
                        String temp="";
                        for (Integer i : periods) {
                            if(i<10) {
                                temp = "\"0" + i + ":00" + "\"";
                            } else {
                                temp = "\"" + i + ":00" + "\"";
                            }
                            datetime.add(temp);
                        }

                        break;
                    }
                    case 2: {
//                    cac moc ngay can lay du lieu
                        periods.add(1);
                        periods.add(7);
                        periods.add(15);
                        periods.add(22);
                        periods.add(30);
                        String temp="";

                        for (Integer i : periods) {
                            if(i<10) {
                                temp = "\"0" + i + "/" + (calendar.get(Calendar.MONTH)+1) + "\"";
                            } else {
                                temp = "\"" + i + "/" +  (calendar.get(Calendar.MONTH)+1) +"\"";
                            }
                            datetime.add(temp);
                        }

                        break;
                    }
                    case 3: {
//                        cac moc thang can lay du lieu
                        periods.add(1);
                        periods.add(3);
                        periods.add(6);
                        periods.add(9);
                        periods.add(12);
                        String temp="";
                        for (Integer i : periods) {
                            if(i<10) {
                                temp =  "\"0" + i + "/" + calendar.get(Calendar.YEAR)  +"\"";
                            } else {
                                temp = "\"" + i + "/" + calendar.get(Calendar.YEAR) +"\"";
                            }
                            System.out.println(temp);

                            datetime.add(temp);
                        }
                        break;
                    }
                    default: {
//                        cac moc nam can lay du lieu
                        for(int i=2020; i<=calendar.get(Calendar.YEAR);i++) {
                            periods.add(i);
                            datetime.add(String.valueOf(i));
                        }

                    }

                }
                sales = OrderDAO.getInstance().selectReportSales(timee,periods);
                revenue = OrderDAO.getInstance().selectReportRevenue(timee,periods);
                customer = OrderDAO.getInstance().selectReportCustomer(timee,periods);

                int salesArr[] = new int[periods.size()];
                int revenueArr[] = new int[periods.size()];
                int customerArr[] = new int[periods.size()];
                String datetimeArr[] = new String[periods.size()];

                for(int i=0;i<periods.size();i++) {
                    datetimeArr[i] = datetime.get(i);
                }

                int j =0;
                if(timee!=1) {
                    for(int i=0;i<sales.size();i+=2) {
                        if(periods.contains(sales.get(i))) {
                            salesArr[j] = sales.get(i+1);
                            revenueArr[j] = revenue.get(i+1);
                            customerArr[j] = customer.get(i+1);
                        }
                        j++;

                    }

                } else {
                    for(int i=0;i<sales.size();i+=2) {
                        if(periods.contains(sales.get(i))) {
                            salesArr[j] = sales.get(i+1);
                            revenueArr[j] = revenue.get(i+1);
                            customerArr[j] = 0;
                        }
                        j++;

                    }
                }

                JsonObject json = new JsonObject();
                json.addProperty("doanhSo", Arrays.toString(salesArr));
                json.addProperty("doanhThu", Arrays.toString(revenueArr));
                json.addProperty("khachHang", Arrays.toString(customerArr));
                json.addProperty("categories", Arrays.toString(datetimeArr));

//
//                response.setContentType("text/html");
//                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json.toString());

                break;
            }
            case "UNBACK": {
                int timee = Integer.parseInt(request.getParameter("timee"));
                int index = Integer.parseInt(request.getParameter("index"));
                System.out.println("time:" +timee);
                System.out.println("index:" +index);
                ArrayList<User> unbacks = CustomerDAO.getInstance().getUnbackCus(timee,index,10);
                System.out.println("unbacks: " + unbacks);
                String html="";
                for(User u : unbacks) {
                    html+= "<div class=\"activity-item d-flex\">\n" +
                            "                                <div class=\"activity-content\">\n" +
                            "                                    "+u.getId() + " | " + u.getName()+"<br/>\n" +
                            "                                    "+u.getEmail() + " | " + u.getPhone()+"<br/>\n" +
                            "                                    Ngày mua gần nhất: "+ (u.getOrderDate()==null?"Chưa bao giờ":u.getOrderDate())+"\n" +
                            "                                </div>\n" +
                            "                            </div>";
                }
                System.out.println(html);

                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req,resp);
    }

    public String renderInfoHTML(int sales, double salesGap) {
        String salesStatus ="";
        if(salesGap > 100) {
            salesStatus = "Tăng";
            salesGap = salesGap -100;
        }
        else if(salesGap <100) {
            salesStatus = "Giảm";
            salesGap = 100 - salesGap;
        } else {
            salesStatus = "Duy trì";
            salesGap=0;
        }
        String html ="";
        html = "                 <h6>"+sales +"</h6>\n" +
                "                                        <span class=\"text-success small pt-1 fw-bold\">"+salesGap+"%</span>\n" +
                "                                        <span\n" +
                "                                            class=\"text-muted small pt-2 ps-1\">"+salesStatus+"</span>";

        return html;
    }

    public String renderTopProHTML(Map<ProductUnit,Integer> res) {
        String html ="";
        for (Map.Entry<ProductUnit, Integer> item : res.entrySet()) {
            String imgurl  =  "./assets/img/products/" + item.getKey().getImg();
           html += "<tr>\n" +
                   "                <th scope=\"row\"><img src=\""+ imgurl+"\"alt=\"\"></th>\n" +
                   "                <td><a href=\"#\" class=\"text-primary fw-bold\"> "+item.getKey().getName()+"</a></td>\n" +
                   "                <td class=\"fw-bold\">"+item.getValue()+"</td>\n" +
                   "            </tr>";

        }

        return html;
    }
}