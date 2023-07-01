public class Department {
    private Integer did;
    private String dname;
    private String function;

    public Department(Integer did, String dname, String function) {
        this.did = did;
        this.dname = dname;
        this.function = function;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
