
package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import controller.ControllerProduct;
import model.DeCart;
import model.User;
import model.cartitem;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws JsonProcessingException {
//
//            ArrayList<DeCart> employeeList = new ArrayList<>();
//            ObjectMapper mapper = new ObjectMapper();
//
//            employeeList.add(new DeCart(3031,10111,1));
//            employeeList.add(new DeCart(3031,10110,2));
////            employeeList.add(new DeCart(3031,3,4));
////            employeeList.add(new DeCart(3031,4,16));
//
//        String json = mapper.writeValueAsString(employeeList);
//
//        Gson gson = new Gson();
//        DeCart[] selectedProducts = gson.fromJson(json, DeCart[].class);
//        System.out.println(selectedProducts.length);
//
//        ArrayList<Integer> idpros = new ArrayList<>();
//        for (int i = 0; i < selectedProducts.length; i++) {
//            idpros.add(selectedProducts[i].getIdProduct());
//        }
//        int idcart = selectedProducts[0].getIdCart();
//        System.out.println(idcart);
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids.add(10110);
//        ids.add(10111);
//
//        System.out.println(idpros);
//
//        ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart,idpros);
//
//        System.out.println(cartTemp);
        User afterU = new User();
        String sex = "nam";
        String birthday = "sinh nhat";
        String message="test";
        String html ="";
        html = "<script> alert(\""+ message +"\"); </script> "+
                "<form action=\"addUpdate-customer\" method=\"post\" id=\"customerInfoForm\">" +
                "                    <div class=\"show-flex-row\">" +
                "                        <h4>"+ (afterU.getName()==""?"Thêm khách hàng mới":"Cập nhật thông tin khách hàng") + "</h4>" +

                "                    </div>" +
                "                    <div class=\"show-flex-row\">" +
                "                        <div class=\"grid__row img-showing\">" +
                "                            <div class=\"disabled-showing" + (afterU.getAvailable()<1?"active":"") + " \" style=\" "+ (afterU.getName()==""?"position: relative;  left: 0":"")  + " \" >" +
                "                                <div class=\"disabled-showing-content\">" +
                "                                    " + (afterU.getAvailable()==0?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA/TẠM KHÓA") +
                "                                </div>" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class=\"stop_reSale " + (afterU.getName()==""?"hide":"") +" \" >" +
                "                            <div class=\"btn btn-stop-pro " + (afterU.getAvailable()>=0?"active":"") + " \">Khóa tài khoản</div>" +
                "                            <div class=\"btn btn-resale-pro " +  (afterU.getAvailable()<0?"active":"") +" \"><a class=\"no-a\"" +
                "                                    href=\"addUpdate-customer?action=unlock&id="+afterU.getId()+"\">Mở khóa tài khoản</a></div>" +

                "                        </div>" +
                "                    </div>" +

                "                    <div class=\"form-group w-50\">" +
                "                        <label class=\"w-20\" for=\"id\">ID</label>" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"id\" name=\"id\" aria-describedby=\"emailHelp\" placeholder=\"ID\" value=\""+afterU.getId()+"\" readonly>" +
                "                    </div>" +

                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"name\">Họ và tên: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"name\" name=\"name\" aria-describedby=\"\" placeholder=\"Nhập tên\" value=\""+afterU.getName()+"\">" +
                "                        <div class=\"required\" hidden>Khong duoc de trong muc nay</div>" +
                "                    </div>" +

                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"email\">Email</label>" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"email\" name=\"email\" aria-describedby=\"\" placeholder=\"Nhập email\" value=\""+afterU.getEmail()+"\">" +
                "                        <div class=\"error\" hidden>Muc nay phai la email</div>" +
                "                        <div class=\"required\" hidden>Khong duoc de trong muc nay</div>" +

                "                    </div>" +
                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"phone\">Số điện thoại: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"phone\" name=\"phone\" aria-describedby=\"\" placeholder=\"Nhập số điện thoại\" value=\""+afterU.getPhone()+"\">" +
                "                        <div class=\"required\" hidden>Khong duoc de trong muc nay</div>" +
                "                        <div class=\"error\" hidden>So dien thoai khong hop le</div>" +

                "                    </div>" +
                "                    <div class=\"form-group w-100\">" +
                "                        <label class=\"w-20\" for=\"address\">Địa chỉ: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"address\" name=\"address\" aria-describedby=\"\" placeholder=\"Nhập Địa chỉ\" value=\""+afterU.getAddress()+"\">" +
                "                        <div class=\"required\" hidden>Khong duoc de trong muc nay</div>" +

                "                    </div>" +

                "                    <div class=\"show-flex-row\">" +

                "                        <div id=\"sex\" class=\"info-container\">" +
                "                            <div class=\"info-container__title\">" +
                "                                <label for=\"sex\">Giới tính" +
                "                                </label>" +
                "                            </div>" +

                "                            <div class=\"info-container__content\">" +
                "                                <div class=\"form-check\">" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault1\" value=\"nam\" "+ (sex.equalsIgnoreCase("nam")?"checked":"") +">" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault1\">" +
                "                                        Nam" +
                "                                    </label>" +
                "                                </div>" +
                "                                <div class=\"form-check\">" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault2\" value=\"nu\" "+ (sex.equalsIgnoreCase("nu") ?"checked" : "")+">" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault2\">" +
                "                                        Nữ" +
                "                                    </label>" +
                "                                </div>" +
                "                            </div>" +

                "                        </div>" +
                "                        <div class=\"form-group w-50\">" +
                "                            <label  class=\"w-20\"  for=\"birthday\">Ngày sinh: </label>" +
                "                            <input type=\"date\" class=\"form-control w-80\" id=\"birthday\" name=\"birthday\"  aria-describedby=\"\" placeholder=\"Enter date import\" value=\""+birthday+"\">" +
                "                            <div class=\"error\" hidden>Chua du 18 tuoi</div>" +

                "                        </div>" +
                "                    </div>" +
                "                    <div class=\"form-group w-50\">" +
                "                        <label  class=\"w-20\"  for=\"datein\">Ngày tham gia: </label>" +
                "                        <input type=\"date\" class=\"form-control w-80\" id=\"datein\" name=\"datein\"  aria-describedby=\"\" placeholder=\"Nhập ngày tham gia\" value=\""+afterU.getDateIn() +"\">" +
                "                    </div>" +

                "                    <div class=\"form-group\" style=\"display: none\">" +
                "                        <label class=\"w-20\" for=\"action\" class=\"input-title\">action</label>" +
                "                        <input type=\"text\" class=\"form-control\" id=\"action\" name=\"action\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\""+(afterU.getName()==""?"add":"update") +"\">" +
                "                    </div>" +
                "                    <div class=\"form-group\" style=\"display: none\">" +
                "                        <label class=\"w-20\" for=\"status\" class=\"input-title\">status</label>" +
                "                        <input type=\"text\" class=\"form-control\" id=\"status\" name=\"status\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\"1\">" +
                "                    </div>" +

                "                    <div class=\"show-flex-row\">" +
                "                        <div class=\"ad_func-container\">" +
                "                            <div><a class=\"btn btn-third\" href=\"goto-customer-admin\">Hủy</a></div>" +
                "                        </div>" +
                "                        <div class=\"ad_func-container\">" +
                "                            <button class=\"btn btn-primary\" type=\"submit\">"+(afterU.getName()==""?"Thêm":"Lưu") +"</button>" +
                "                        </div>" +
                "                    </div>" +
                "                    <!--                    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>-->" +
                "                </form>";
        System.out.println(html);
    }


}

//package database;
//
//import model.User;
//
//public class test {
//    public static void main(String[] args) {
//        String name = "Tran Thi Thuy";
//        String email = "trongtin3152@gmail.com";
//        String password = "123";
//        String phone = "123";
//        String address = "265";
//        String info = "nam - 2000";
//
//        User user = new User(name, email, password, phone, address, info);
//        user.setLevel(0);
//        UserDAO userDAO = UserDAO.getInstance();
//        userDAO.insert(user);
//    }
//}
//

