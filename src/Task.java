import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Task {

    public static void main(String args[] ) {
        String line = "";
        String splitBy = ";";

        List<Structure> list = new ArrayList<>();
        List<Structure> mainStructure = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Jovanovski\\Desktop\\Interview\\Navigation.csv"));
            br.readLine();
            while ((line=br.readLine()) != null) {
                String[] navigation = line.split(splitBy);    // use ; as separator Int,String,Int,False,String
                Structure structure = new Structure(Integer.parseInt(navigation[0]),navigation[1],navigation[2],navigation[3],navigation[4]);
                if(navigation[3].equals("False"))
                    list.add(structure);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        while(list.size()!=0 ) {
            for (int i=0; i<list.size();i++) {
                if (list.get(i).getIsHidden().equals("False")) {
                    if (list.get(i).getParentId().equals("NULL")) { // NULTO NIVO
                        mainStructure.add(list.get(i));

                        list.remove(i);
                    } else {

                        int parent = Integer.parseInt(list.get(i).getParentId());
                        for (Structure s : mainStructure) { // PRVO NIVO
                            if (s.getId() == parent) {
                                s.getChildrenList().add(list.get(i));
                                list.remove(i);
                            }
                            else {

                                Structure tmp = s.searchID(parent,s.getChildrenList()); // N-ary
                                if(tmp != null) {
                                    tmp.getChildrenList().add(list.get(i));
                                    list.remove(i);
                                }
                            }
                        }
                    }

                }
            }
        }


        for (Structure structure : mainStructure) {
            Collections.sort(structure.getChildrenList(), new Comparator<Structure>() {
                @Override
                public int compare(Structure structure, Structure t1) {
                    return structure.getName().compareToIgnoreCase(t1.getName());
                }
            });
            System.out.print(".");
            structure.printStructure();
        }





    }

}
