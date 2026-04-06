public class Bus {
    private String id;
    private String route;
    private String time;
    private String busstop;
    private int pcapacity;
    private String finish;
    //private String condoctor;
    Bus(String id, String route, String time, String busstop, int pcapacity, String finish){
        this.id = id;
        this.route = route;
        this.time = time;
        this.busstop = busstop;
        this.pcapacity = pcapacity;
        this.finish = finish;
        //this.condoctor = condoctor;
    }

    public String getid(){return id;}
    public String getroute(){return route;}
    public String gettime(){return time;}
    public String getbusstop(){return busstop;}
    public String getfinish(){return finish;}
    //public String getcondoctor(){return condoctor;}
    public int getpcapacity(){return pcapacity;}

    public void setid(String id){this.id = id;}
    public void setroute(String route){this.route = route;}
    public void settim(String time){this.time = time;}
    public void setbusstop(String busstop){this.busstop = busstop;}
    public void setfinish(String finish){this.finish = finish;}
    //public void setcondoctor(String condoctor){this.condoctor = condoctor;}
    public void setpcapacity(int pcapacity){this.pcapacity = pcapacity;}

}
