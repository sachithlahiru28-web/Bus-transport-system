import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main {
    

    static ArrayList<Bus>buses = new ArrayList<>();
    static ArrayList<Driver>drivers  = new ArrayList<>();
    static ArrayList<Condoctor>condoctors = new ArrayList<>();
    static String role = "";
     public static void main(String[] args) {

        fileloadingbus();
        fileloadingdriver();
        fileloadingcondoctor();
        login();
     }

     public static void login(){
        JFrame loginjf = new JFrame("Loging");
        loginjf.setSize(300, 250);
        loginjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginjf.setLocationRelativeTo(null);
        loginjf.setLayout(null);

        JLabel fordriverjl = new JLabel("For Drivers/Condoctors:");
        fordriverjl.setBounds(10, 10, 150, 25);
        loginjf.add(fordriverjl);

        JLabel drivernamejl = new JLabel("Name");
        drivernamejl.setBounds(10, 40, 80, 25);
        loginjf.add(drivernamejl);

        JTextField drivernametf = new JTextField();
        drivernametf.setBounds(80, 40, 150, 25);
        loginjf.add(drivernametf);

        JLabel driveridjl = new JLabel("ID No.");
        driveridjl.setBounds(10, 70, 80, 25);
        loginjf.add(driveridjl);

        JTextField driveridtf = new JTextField();
        driveridtf.setBounds(80, 70, 150, 25);
        loginjf.add(driveridtf);

        JButton loginbtn = new JButton("Loging");
        loginbtn.setBounds(110, 110, 80, 25);
        loginjf.add(loginbtn);
        
        JLabel passengerjl = new JLabel("For Passengers:");
        passengerjl.setBounds(10, 140, 100, 25);
        loginjf.add(passengerjl);

        JButton shediulebtn = new JButton("View Shedule");
        shediulebtn.setBounds(80, 170, 150, 25);
        loginjf.add(shediulebtn);

        loginbtn.addActionListener( e-> {
            String name = drivernametf.getText();
            String id = driveridtf.getText();
            boolean found = false;
            if(name.isEmpty() || id.isEmpty()){
                JOptionPane.showMessageDialog(loginbtn, "Pleace enter all details");
            }else{
            for(Driver d : drivers){
                if(d.getname().equals(name) && d.getid().equals(id)){
                    JOptionPane.showMessageDialog(loginbtn, "successfully loging as a driver");
                    found = true;
                    loginjf.dispose();
                    drivermenu();
                }
            }
            for(Condoctor c : condoctors){
                if(c.getname().equals(name) && c.getid().equals(id)){
                    JOptionPane.showMessageDialog(loginbtn, "successfully loging as a condoctor");
                    found = true;
                    loginjf.dispose();
                    drivermenu();
                }
            }

            if(!found){ JOptionPane.showMessageDialog(loginbtn, "invalid name or id");}
        }
        });

        shediulebtn.addActionListener(e -> {
            loginjf.dispose();
            passengermenu();
        });


        loginjf.setVisible(true);
     }

     public static void drivermenu(){

        role = "driver";

        JFrame drivermenujf = new JFrame("Driver menu");
        drivermenujf.setSize(400, 400);
        drivermenujf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drivermenujf.setLocationRelativeTo(null);
        drivermenujf.setLayout(new GridLayout(10, 1) );

        JButton adddriverjb = new JButton("Add driver");
        drivermenujf.add(adddriverjb);

        JButton addcondoctorsjb = new JButton("Add condoctor");
        drivermenujf.add(addcondoctorsjb);

        JButton viewdriversjb = new JButton("View Drivers");
        drivermenujf.add(viewdriversjb);

        JButton viewcondoctorjb = new JButton("View condoctors");
        drivermenujf.add(viewcondoctorjb);

        JButton addturnjb = new JButton("Add your turn");
        drivermenujf.add(addturnjb);

        JButton updateshedulejb = new JButton("Update shedule");
        drivermenujf.add(updateshedulejb);

        JButton markjb = new JButton("Mark the Turn");
        drivermenujf.add(markjb);

        JButton deletedriversjb = new JButton("Delete drivers");
        drivermenujf.add(deletedriversjb);

        JButton deletecondoctorsjb = new JButton("Delete condoctor");
        drivermenujf.add(deletecondoctorsjb);

        JButton logoutjb = new JButton("Logout");
        drivermenujf.add(logoutjb);

        adddriverjb.addActionListener(e -> {
            drivermenujf.dispose();
            adddriver();
        });

        addcondoctorsjb.addActionListener(e -> {
            drivermenujf.dispose();
            addcondoctor();
        });

        viewdriversjb.addActionListener(e -> {
            if(drivers.isEmpty()){JOptionPane.showMessageDialog(viewdriversjb, "Drivers lists is empty");}
            else{
                drivermenujf.dispose();
                viewdrivers();
            }
        });

        viewcondoctorjb.addActionListener(e -> {
            if(condoctors.isEmpty()){JOptionPane.showMessageDialog(viewcondoctorjb, "Condoctor list is empty");}
            else{drivermenujf.dispose();
                viewcondoctor();
            }
        });

        addturnjb.addActionListener(e -> {
            drivermenujf.dispose();
            addturn();
        });

        updateshedulejb.addActionListener(e -> {
            drivermenujf.dispose();
            updateshedule();
        });

        markjb.addActionListener(e -> {
            drivermenujf.dispose();
            mark();
        });

        deletedriversjb.addActionListener(e -> {
            drivermenujf.dispose();
            deletedrivers();
        });

        deletecondoctorsjb.addActionListener(e -> {
            drivermenujf.dispose();
            deletecondoctors();
        });

        logoutjb.addActionListener(e -> {
            drivermenujf.dispose();
            login();
        });

        drivermenujf.setVisible(true);
     }

     public static void passengermenu(){
        role = "passenger";

        JFrame shedulejf = new JFrame("Shedule");
        shedulejf.setSize(700, 500);
        shedulejf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shedulejf.setLocationRelativeTo(null);
        shedulejf.setLayout(new BorderLayout());

        JButton back = new JButton("Back");
        shedulejf.add(back, BorderLayout.SOUTH);

        String[] colums = {"ID", "Route", "Time", "Bus stops", "p.Capacity", "Finish"};
        String[][] data = new String[buses.size()][6];
        for(int i = 0; i < buses.size(); i++){
            Bus b = buses.get(i);
            data[i][0] = b.getid();
            data[i][1] = b.getroute();
            data[i][2] = b.gettime();
            data[i][3] = b.getbusstop();
            data[i][4] = Integer.toString(b.getpcapacity());
            data[i][5] = b.getfinish();
        }

        JTable table = new JTable(data, colums);
        JScrollPane scrollPane = new JScrollPane(table);
        shedulejf.add(scrollPane);

        back.addActionListener(e -> {
            shedulejf.dispose();
            if(role.equals("driver")){
            drivermenu();
            }
            if(role.equals("passenger")){
                login();
            }
        });

        shedulejf.setVisible(true);
     }

     public static void adddriver(){
        JFrame adddriverjf = new JFrame("Add driver");
        adddriverjf.setSize(400, 400);
        adddriverjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adddriverjf.setLocationRelativeTo(null);
        adddriverjf.setLayout(null);

        JLabel namejl = new JLabel("Name");
        namejl.setBounds(10, 20, 80, 25);
        adddriverjf.add(namejl);

        JTextField nametf = new JTextField();
        nametf.setBounds(80, 20, 150, 25);
        adddriverjf.add(nametf);

        JLabel idjl = new JLabel("ID No");
        idjl.setBounds(10, 50, 80, 25);
        adddriverjf.add(idjl);

        JTextField idtf = new JTextField();
        idtf.setBounds(80, 50, 150, 25);
        adddriverjf.add(idtf);

        JButton back = new JButton("Back");
        back.setBounds(80, 100, 80, 25);
        adddriverjf.add(back);

        JButton save = new JButton("Save");
        save.setBounds(175, 100, 80, 25);
        adddriverjf.add(save);

        back.addActionListener(e -> {
            adddriverjf.dispose();
            drivermenu();
        });

        save.addActionListener(e -> {
            String name = nametf.getText();
            String id = idtf.getText();

            if(name.isEmpty() || id.isEmpty()){
                JOptionPane.showMessageDialog(save, "Pleace enter all details");
            }else{
            drivers.add(new Driver(name, id));
            filesavingdrivers();
            JOptionPane.showMessageDialog(save, "details seved successfully");
            }
        });

        adddriverjf.setVisible(true);
     }

     public static void viewdrivers(){
        JFrame viewdriverjf = new JFrame("Drivers details");
        viewdriverjf.setSize(300, 400);
        viewdriverjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewdriverjf.setLocationRelativeTo(null);
        viewdriverjf.setLayout(new BorderLayout());

        JButton back = new JButton("Back");
        viewdriverjf.add(back, BorderLayout.SOUTH);

        String[] colums = {"Name", "ID No."};
        String[][] data = new String[drivers.size()][2];
        for(int i = 0; i <drivers.size(); i++){
            Driver d = drivers.get(i);
            data[i][0] = d.getname();
            data[i][1] = d.getid();
        }

        JTable table = new JTable(data, colums);
        JScrollPane scrollPane = new JScrollPane(table);
        viewdriverjf.add(scrollPane);

        back.addActionListener(e -> {
            viewdriverjf.dispose();
            drivermenu();
        });


        viewdriverjf.setVisible(true);
     }

     public static void addturn(){
        JFrame addturnjf = new JFrame("Turn details");
        addturnjf.setSize(400, 500);
        addturnjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addturnjf.setLocationRelativeTo(null);
        addturnjf.setLayout(null);

        JLabel busidjl = new JLabel("Bus ID");
        busidjl.setBounds(10, 20, 80, 25);
        addturnjf.add(busidjl);

        JTextField busidtf = new JTextField();
        busidtf.setBounds(80, 20, 150, 25);
        addturnjf.add(busidtf);

        JLabel routejl = new JLabel("Route");
        routejl.setBounds(10, 50, 80, 25);
        addturnjf.add(routejl);

        JTextField routetf = new JTextField();
        routetf.setBounds(80, 50, 150, 25);
        addturnjf.add(routetf);

        JLabel timejl = new JLabel("Time");
        timejl.setBounds(10, 80, 80, 25);
        addturnjf.add(timejl);

        JTextField timetf = new JTextField();
        timetf.setBounds(80, 80, 150, 25);
        addturnjf.add(timetf);

        JLabel busstopjl = new JLabel("B.Stops");
        busstopjl.setBounds(10, 110, 80, 25);
        addturnjf.add(busstopjl);

        JTextField busstoptf = new JTextField();
        busstoptf.setBounds(80, 110, 150, 25);
        addturnjf.add(busstoptf);

        JLabel finishjl = new JLabel("Finish");
        finishjl.setBounds(10, 140, 80, 25);
        addturnjf.add(finishjl);

        JTextField finishtf = new JTextField();
        finishtf.setBounds(80, 140, 150, 25);
        addturnjf.add(finishtf);

        JLabel pcapacityjl = new JLabel("P.Capacity");
        pcapacityjl.setBounds(10, 170, 80, 25);
        addturnjf.add(pcapacityjl);

        JTextField pcapacitytf = new JTextField();
        pcapacitytf.setBounds(80, 170, 150, 25);
        addturnjf.add(pcapacitytf);

        JButton back = new JButton("Back");
        back.setBounds(80, 210, 80, 25);
        addturnjf.add(back);

        JButton addjb = new JButton("Add");
        addjb.setBounds(175, 210, 80, 25);
        addturnjf.add(addjb);

        back.addActionListener(e -> {
            addturnjf.dispose();
            drivermenu();
        });

        addjb.addActionListener(e -> {
            String id = busidtf.getText();
            String route = routetf.getText();
            String time = timetf.getText();
            String busstop = busstoptf.getText();
            String finish = finishtf.getText();
            int pcapacity = Integer.parseInt(pcapacitytf.getText());

            if(id.isEmpty() || route.isEmpty()){
                JOptionPane.showMessageDialog(addjb, "Complete the details");
            }else if(time.isEmpty()){
                JOptionPane.showMessageDialog(addjb, "Complete the details");
            }else{
                //String[] busstops = busstop.split(",");
                buses.add(new Bus(id, route, time, busstop, pcapacity, finish));
                filesavingbus();
                JOptionPane.showMessageDialog(addjb, "details added");
                addturnjf.dispose();
                passengermenu();
            }

        });

        addturnjf.setVisible(true);
     }

     public static void addcondoctor(){
        JFrame addcondoctorjf = new JFrame("Add Condoctor");
        addcondoctorjf.setSize(400, 400);
        addcondoctorjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addcondoctorjf.setLocationRelativeTo(null);
        addcondoctorjf.setLayout(null);

        JLabel cnamejl = new JLabel("Name");
        cnamejl.setBounds(10, 20, 80, 25);
        addcondoctorjf.add(cnamejl);

        JTextField cnametf = new JTextField();
        cnametf.setBounds(80, 20, 150, 25);
        addcondoctorjf.add(cnametf);

        JLabel cidjl = new JLabel("ID");
        cidjl.setBounds(10, 50, 80, 25);
        addcondoctorjf.add(cidjl);

        JTextField cidtf = new JTextField();
        cidtf.setBounds(80, 50, 150, 25);
        addcondoctorjf.add(cidtf);

        JButton back = new JButton("Back");
        back.setBounds(80, 100, 80, 25);
        addcondoctorjf.add(back);

        JButton savejb = new JButton("Save");
        savejb.setBounds(175, 100, 80, 25);
        addcondoctorjf.add(savejb);

        back.addActionListener(e -> {
            addcondoctorjf.dispose();
            drivermenu();
        });

        savejb.addActionListener(e -> {
            String name = cnametf.getText();
            String id = cidtf.getText();
            if(name.isEmpty()||id.isEmpty()){
                JOptionPane.showMessageDialog(savejb, "Pleace enter evry details");
            }else{
                condoctors.add(new Condoctor(name, id));
                filesavingcodoctors();
                JOptionPane.showMessageDialog(savejb, "details saved");
            }
        });

        addcondoctorjf.setVisible(true);
     }

     public static void viewcondoctor(){
        JFrame viewcodoctorjf = new JFrame("View Condoctors");
        viewcodoctorjf.setSize(400, 400);
        viewcodoctorjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewcodoctorjf.setLocationRelativeTo(null);
        viewcodoctorjf.setLayout(new BorderLayout());

        JButton back = new JButton("Back");
        viewcodoctorjf.add(back, BorderLayout.SOUTH);

        String[] colums = {"Name", "ID"};
        String[][] data = new String[condoctors.size()][2];
        for(int i = 0; i < condoctors.size(); i++){
            Condoctor c = condoctors.get(i);
            data[i][0] = c.getname();
            data[i][1] = c.getid();
        }

        JTable table = new JTable(data, colums);
        JScrollPane jScrollPane = new JScrollPane(table);
        viewcodoctorjf.add(jScrollPane);

        back.addActionListener(e -> {
            viewcodoctorjf.dispose();
            drivermenu();
        });

        viewcodoctorjf.setVisible(true);
     }

     public static void updateshedule(){
        JFrame updateshejf = new JFrame("Update shedule");
        updateshejf.setSize(300, 200);
        updateshejf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateshejf.setLocationRelativeTo(null);
        updateshejf.setLayout(null);

        JLabel busidjl = new JLabel("Bus ID");
        busidjl.setBounds(10, 20, 100, 25);
        updateshejf.add(busidjl);

        JTextField busidtf = new JTextField();
        busidtf.setBounds(100, 20, 150, 25);
        updateshejf.add(busidtf);

        JButton back = new JButton("Back");
        back.setBounds(100, 80, 80, 25);
        updateshejf.add(back);
        
        JButton continuejb = new JButton("Continue");
        continuejb.setBounds(190, 80, 80, 25);
        updateshejf.add(continuejb);

        back.addActionListener(e -> {
            updateshejf.dispose();
            drivermenu();
        });

        continuejb.addActionListener(e -> {
            String id = busidtf.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(continuejb, "Pleace enter id No.");
            }
            boolean found = false;
            
            for(Bus b : buses){
                if(b.getid().equals(id)){
                    found = true;
                    break;
                       
                }
            }

            if(found){
                updateshejf.dispose();
                updates();
            }else{JOptionPane.showMessageDialog(continuejb, "invalid id");}
                    
        });

        updateshejf.setVisible(true);
     }

     public static void updates(){
        JFrame updatesjf = new JFrame("Update shedule");
        updatesjf.setSize(400, 400);
        updatesjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updatesjf.setLocationRelativeTo(null);
        updatesjf.setLayout(null);

        JLabel newbidjl = new JLabel("New Id");
        newbidjl.setBounds(10, 20, 100, 25);
        updatesjf.add(newbidjl);

        JTextField newbidtf = new JTextField();
        newbidtf.setBounds(100, 20, 150, 25);
        updatesjf.add(newbidtf);

        JLabel newroutejl = new JLabel("New route");
        newroutejl.setBounds(10, 50, 100, 25);
        updatesjf.add(newroutejl);

        JTextField newroutetf = new JTextField();
        newroutetf.setBounds(100, 50, 150, 25);
        updatesjf.add(newroutetf);

        JLabel newtimejl = new JLabel("New time");
        newtimejl.setBounds(10, 80, 100, 25);
        updatesjf.add(newtimejl);

        JTextField newtimetf = new JTextField();
        newtimetf.setBounds(100, 80, 150, 25);
        updatesjf.add(newtimetf);

        JLabel newbusstopjl = new JLabel("New B.Stops");
        newbusstopjl.setBounds(10, 110, 100, 25);
        updatesjf.add(newbusstopjl);

        JTextField newbusstoptf = new JTextField();
        newbusstoptf.setBounds(100, 110, 150, 25);
        updatesjf.add(newbusstoptf);

        JLabel newpcapacityjl = new JLabel("New P.capacity");
        newpcapacityjl.setBounds(10, 140, 100, 25);
        updatesjf.add(newpcapacityjl);

        JTextField newpcapacitytf = new JTextField();
        newpcapacitytf.setBounds(100, 140, 150, 25);
        updatesjf.add(newpcapacitytf);

        JLabel newfinishjl = new JLabel("Finish");
        newfinishjl.setBounds(10, 170, 100, 25);
        updatesjf.add(newfinishjl);

        JTextField newfinishtf = new JTextField();
        newfinishtf.setBounds(100, 170, 150, 25);
        updatesjf.add(newfinishtf);

        JButton back = new JButton("Back");
        back.setBounds(100, 210, 80, 25);
        updatesjf.add(back);

        JButton savejb = new JButton("Save");
        savejb.setBounds(200, 210, 80, 25);
        updatesjf.add(savejb);

        back.addActionListener(e -> {
            updatesjf.dispose();
            drivermenu();
        });

        savejb.addActionListener(e -> {
            String id = newbidtf.getText();
            String route = newroutetf.getText();
            String time = newtimetf.getText();
            String busstop = newbusstoptf.getText();
            int pcapacity = Integer.parseInt(newpcapacitytf.getText());
            String finish = newfinishtf.getText();

            //String[] busstops = busstop.split(",");

            for(Bus b : buses){
                b.setid(id);
                b.setroute(route);
                b.settim(time);
                b.setbusstop(busstop);
                b.setpcapacity(pcapacity);
                b.setfinish(finish);
                break;
                
            }
            filesavingbus();
            JOptionPane.showMessageDialog(savejb, "Details updated");
            updatesjf.dispose();
            passengermenu();

        });

        updatesjf.setVisible(true);
     }

     public static void mark(){
        JFrame markjf = new JFrame("Mark the turn");
        markjf.setSize(300, 200);
        markjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        markjf.setLocationRelativeTo(null);
        markjf.setLayout(null);

        JLabel bidjl = new JLabel("Bus Id");
        bidjl.setBounds(10, 20, 100, 25);
        markjf.add(bidjl);

        JTextField bidtf = new JTextField();
        bidtf.setBounds(100, 20, 160, 25);
        markjf.add(bidtf);

        JButton back = new JButton("Back");
        back.setBounds(100, 80, 80, 25);
        markjf.add(back);

        JButton continuejb = new JButton("Continue");
        continuejb.setBounds(190, 80, 80, 25);
        markjf.add(continuejb);

        back.addActionListener(e -> {
            markjf.dispose();
            drivermenu();
        });

        continuejb.addActionListener(e -> {
            String id = bidtf.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(continuejb, "Pleace enter the id");
            }
            boolean found = false;
            for(Bus b : buses){
                if(b.getid().equals(id)){
                    found = true;
                    break;
                }
            }
            if(found){
                markjf.dispose();
                markt();
            }else{JOptionPane.showMessageDialog(continuejb, "invalid id");}

        });

        markjf.setVisible(true);
     }

     public static void markt(){
        JFrame marktjf = new JFrame("Mark your turn");
        marktjf.setSize(300, 300);
        marktjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marktjf.setLocationRelativeTo(null);
        marktjf.setLayout(null);

        JLabel finishjl = new JLabel("Finish");
        finishjl.setBounds(10, 20, 80, 25);
        marktjf.add(finishjl);

        JTextField finishtf = new JTextField();
        finishtf.setBounds(80, 20, 150, 25);
        marktjf.add(finishtf);

        JButton back = new JButton("Back");
        back.setBounds(80, 80, 80, 25);
        marktjf.add(back);

        JButton savejb = new JButton("Save");
        savejb.setBounds(170, 80, 80, 25);
        marktjf.add(savejb);

        back.addActionListener(e -> {
            marktjf.dispose();
            drivermenu();
        });

        savejb.addActionListener(e -> {
            String finish = finishtf.getText();
            if(finish.isEmpty()){
                JOptionPane.showMessageDialog(savejb, "Pleace enter yes/no");
            }else{
            for(Bus b : buses){
                b.setfinish(finish);
            }
            JOptionPane.showMessageDialog(savejb, "updated");
        }
        });

        marktjf.setVisible(true);
     }

     public static void deletedrivers(){
        JFrame deletedriversjf = new JFrame("Delete drivers");
        deletedriversjf.setSize(300, 200);
        deletedriversjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deletedriversjf.setLocationRelativeTo(null);
        deletedriversjf.setLayout(null);

        JLabel driverid = new JLabel("ID No");
        driverid.setBounds(10, 20, 80, 25);
        deletedriversjf.add(driverid);

        JTextField driveridtf = new JTextField();
        driveridtf.setBounds(80, 20, 150, 25);
        deletedriversjf.add(driveridtf);

        JButton back = new JButton("Back");
        back.setBounds(80, 80, 80, 25);
        deletedriversjf.add(back);

        JButton deletejb = new JButton("Delete");
        deletejb.setBounds(175, 80, 80, 25);
        deletedriversjf.add(deletejb);

        back.addActionListener(e -> {
        deletedriversjf.dispose();
        drivermenu();
        });

        deletejb.addActionListener(e -> {
            String id = driveridtf.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(deletejb, "Pleace enter thr id");
            }else{for(Driver d : drivers){
                if(drivers.removeIf(driver -> d.getid().equals(id))){
                    JOptionPane.showMessageDialog(deletejb, "driver details deleted");
                }else{JOptionPane.showMessageDialog(deletejb, "invalid id");}
            }}
            
        });

        deletedriversjf.setVisible(true);
     }

     public static void deletecondoctors(){
        JFrame deletecodjf = new JFrame("Delete condoctor");
        deletecodjf.setSize(300, 200);
        deletecodjf.setLocationRelativeTo(null);
        deletecodjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deletecodjf.setLayout(null);

        JLabel conidjl = new JLabel("ID No");
        conidjl.setBounds(10, 20, 80, 25);
        deletecodjf.add(conidjl);

        JTextField conidtf = new JTextField();
        conidtf.setBounds(80, 20, 150, 25);
        deletecodjf.add(conidtf);

        JButton back = new JButton("Back");
        back.setBounds(80, 80, 80, 25);
        deletecodjf.add(back);

        JButton deletejb =new JButton("Delete");
        deletejb.setBounds(175, 80, 80, 25);
        deletecodjf.add(deletejb);

        back.addActionListener(e -> {
            deletecodjf.dispose();
            drivermenu();
        });

        deletejb.addActionListener(e -> {
            String id = conidtf.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(deletejb, "pleace enter the id");
            }else{
                for(Condoctor c : condoctors){
                    if(condoctors.removeIf(condoctor -> c.getid().equals(id))){
                        JOptionPane.showMessageDialog(deletejb, "details deleted");
                    }else{JOptionPane.showMessageDialog(deletejb, "invalid id");}
                }
            }
        });

        deletecodjf.setVisible(true);
     }

     public static void filesavingbus(){
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Bus.txt")) )){
        for(Bus b : buses){
           writer.println(b.getid());
           writer.println(b.getroute());
           writer.println(b.gettime());
           writer.println(b.getbusstop());
           writer.println(b.getpcapacity());
           writer.println(b.getfinish());
           writer.println("/");
        }
        
        JOptionPane.showMessageDialog(null, "file saved");
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

     public static void filesavingdrivers(){
        try(FileOutputStream fos = new FileOutputStream("Driver.txt");
    BufferedOutputStream bos = new BufferedOutputStream(fos)){
        for(Driver d : drivers){
            String record = d.getname() + "," + d.getid() + "\n";
            bos.write(record.getBytes());
        }
        bos.flush();
        JOptionPane.showMessageDialog(null, "file saved");
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

     public static void filesavingcodoctors(){
        try(FileOutputStream fos = new FileOutputStream("Condoctor.txt");
    BufferedOutputStream bos = new BufferedOutputStream(fos)){
        for(Condoctor c : condoctors){
            String record = c.getname() + "," + c.getid() + "\n";
            bos.write(record.getBytes());
        }
        bos.flush();
        JOptionPane.showMessageDialog(null, "file saved");
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

     public static void fileloadingbus(){
        File file = new File("Bus.txt");
        if(!file.exists()) return;

        buses.clear();

        try(Scanner filescanner = new Scanner(file) ){
        while (filescanner.hasNextLine()) {
            //String line = filescanner.nextLine();
            //if(line.isEmpty()) continue;
            
            String id = filescanner.nextLine().trim();
            String route = filescanner.nextLine().trim();
            String time = filescanner.nextLine().trim();
            String busstop = filescanner.nextLine().trim();
            String capacityString = filescanner.nextLine().trim();
            int pcapacity = Integer.parseInt(capacityString);
            String finish = filescanner.nextLine().trim();

            if(filescanner.hasNextLine()){
                filescanner.nextLine();
            }

            buses.add(new Bus(id, route, time, busstop, pcapacity, finish));
        }
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

     public static void fileloadingdriver(){
        File file = new File("Driver.txt");
        if(!file.exists()) return;

        try(FileInputStream fis = new FileInputStream(file);
    Scanner filescanner = new Scanner(file)){
        while (filescanner.hasNextLine()) {
            String line = filescanner.nextLine(); 
            if(line.isEmpty()) continue;
            String[] data = line.split(",");
            String name = data[0].trim();
            String id = data[1].trim();
            drivers.add(new Driver(name, id));
        }
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

     public static void fileloadingcondoctor(){
        File file = new File("Condoctor.txt");
        if(!file.exists()) return;

        try(FileInputStream fis = new FileInputStream(file);
    Scanner filescanner = new Scanner(file)){
        while (filescanner.hasNextLine()) {
            String line = filescanner.nextLine();
            if(line.isEmpty()) continue;
            String[] data = line.split(",");
            String name = data[0].trim();
            String id = data[1].trim();
            condoctors.add(new Condoctor(name, id));
        }
    }catch(IOException e){JOptionPane.showMessageDialog(null, e.getMessage());}
     }

}