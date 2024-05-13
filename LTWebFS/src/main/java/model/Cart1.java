package model;

public class Cart1 {
    private int id;
    private int idUser;

    public Cart1(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public Cart1() {
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", idUser=" + idUser +
                '}';
    }
}
