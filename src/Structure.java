import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Structure {
    int id=0;
    String parentId;
    String name,linkURL;
    String isHidden;
    ArrayList<Structure> children = new ArrayList<>();
    int level=0;

    public Structure(int id, String name, String parentId, String isHidden, String linkURL) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.linkURL = linkURL;
        this.isHidden = isHidden;
        children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public ArrayList<Structure> getChildrenList() {
        return children;
    }

    public int getLevel() {
        return this.linkURL.length() - this.linkURL.replace("/", "").length();
    }

    public Structure searchID(int checkId,ArrayList<Structure> next) {
        Structure found = null;
        for (Structure s : next) {
            if (s.getId() == checkId) {
                found = s;
            } else {
                found = searchID(checkId, s.children);
            }
        }

        if (found != null) return found;
        else return null;
    }

    public void printStructure() {
        System.out.println(getName());
        for(Structure s : children) {
            System.out.print(".");
            for(int i=0;i<s.getLevel()-1;i++) {
                System.out.print("...");
            }
            s.printStructure();
        }
    }
}
