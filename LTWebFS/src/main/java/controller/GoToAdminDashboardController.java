package controller;

import com.google.gson.JsonObject;
import database.CustomerDAO;
import database.OrderDAO;
import database.ProductUnitDAO;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/goto-dashboard-admin")
public class GoToAdminDashboardController extends HttpServlet {
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


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

        int salesArr[] = new int[periods.size()];
        int revenueArr[] = new int[periods.size()];
        int customerArr[] = new int[periods.size()];
        String datetimeArr[] = new String[periods.size()];

        for(int i=0;i<periods.size();i++) {
            datetimeArr[i] = datetime.get(i);
        }

        int j =0;

        for(int i=0;i<salesReport.size();i+=2) {
            if(periods.contains(salesReport.get(i))) {
                salesArr[j] = salesReport.get(i+1);
                revenueArr[j] = revenueReport.get(i+1);
                customerArr[j] = customerReport.get(i+1);
            }
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
}
