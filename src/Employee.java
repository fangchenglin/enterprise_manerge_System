
public class Employee {
    private Integer eid;
    private Integer sid;
    private Integer did;
    private String ename;
    private String dname;
    private String gender;
    private Integer salary;
    private String class_level;
    private String award;

    public Employee(Integer eid, Integer sid, Integer did, String ename, String gender, String class_level,String dname,Integer salary) {
        this.eid = eid;
        this.sid = sid;
        this.did = did;
        this.ename = ename;
        this.gender = gender;
        this.class_level = class_level;
        this.dname = dname;
        this.salary = salary;
    }


    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClass_level() {
        return class_level;
    }

    public void setClass_level(String class_level) {
        this.class_level = class_level;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
