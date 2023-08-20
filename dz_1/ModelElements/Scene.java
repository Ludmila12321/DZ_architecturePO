package ModelElements;
import java.util.List;
public class Scene {
    public int Id;
    public List<PoligonalModel> Models;
    public List<Flash> Flashes;
    public List<Camera> Cameras;
    
    public Scene(int id,  List<PoligonalModel> models, List<Flash> flashes, List<Camera> cameras ) throws Exception {
    
        Id = id;
        if (models.size() > 0) {
            Models = models;
        } else throw new Exception("Должна быть хотя бы одна модель");
        
        Flashes = flashes;
         if (models.size() > 0) {
            Cameras = cameras;
        } else throw new Exception("Должна быть хотя бы одна модель");
    }
}
