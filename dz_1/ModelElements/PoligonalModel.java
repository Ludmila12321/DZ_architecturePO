package ModelElements;
import java.util.ArrayList;
import java.util.List;
import Stuff.Point3D;
public class PoligonalModel {
    public List<Texture> Textures;

    public List<Poligon> Poligons;

    public PoligonalModel(List<Texture> textures) {
        Textures = textures;
        Poligons = new ArrayList<>();
        List<Point3D> result = new ArrayList<>();
        Poligons.add(new Poligon(result));
    }
}
