package database;

import model.Brand;

public class test {
    public static void main(String[] args) {
        Brand br = new Brand("nokia");
        BrandDAO.getInstance().insert(br);
        System.out.println(BrandDAO.getInstance().selectAll());
    }

}

