package java_demo.fastjson;

import java.io.Serializable;
import java.util.List;

public class PersonComplex implements Serializable {
    private static final long serialVersionUID=1L;

    private String name;
    private String address;
    private int phone;
    private List<Integer> ids;
    private List<Friend> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "PersonComplex{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", ids=" + ids +
                ", friends=" + friends +
                '}';
    }
}
