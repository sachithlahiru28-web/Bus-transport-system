public class Driver {
    private String name;
    private String id;
    //private String mark;
    Driver(String name, String id){
        this.name = name;
        this.id = id;
        //this.mark =mark;
    }
    public String getname(){return name;}
    public String getid(){return id;}
    //public String getmark(){return mark;}

    public void setname(String name){this.name = name;}
    public void setid(String id){this.id = id;}
    //public void setmark(String mark){this.mark = mark;}
}
