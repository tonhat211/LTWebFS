
package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import controller.ControllerProduct;
import model.DeCart;
import model.cartitem;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws JsonProcessingException {

            ArrayList<DeCart> employeeList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();

            employeeList.add(new DeCart(3031,10111,1));
            employeeList.add(new DeCart(3031,10110,2));
//            employeeList.add(new DeCart(3031,3,4));
//            employeeList.add(new DeCart(3031,4,16));

        String json = mapper.writeValueAsString(employeeList);

        Gson gson = new Gson();
        DeCart[] selectedProducts = gson.fromJson(json, DeCart[].class);
        System.out.println(selectedProducts.length);

        ArrayList<Integer> idpros = new ArrayList<>();
        for (int i = 0; i < selectedProducts.length; i++) {
            idpros.add(selectedProducts[i].getIdProduct());
        }
        int idcart = selectedProducts[0].getIdCart();
        System.out.println(idcart);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(10110);
        ids.add(10111);

        System.out.println(idpros);

        ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart,idpros);

        System.out.println(cartTemp);

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

