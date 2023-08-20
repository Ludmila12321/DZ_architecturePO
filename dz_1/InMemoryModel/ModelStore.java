package InMemoryModel;
import java.util.ArrayList;
import java.util.List;
import ModelElements.Camera;
import ModelElements.Flash;
import ModelElements.PoligonalModel;
import ModelElements.Scene;
public class ModelStore implements IModelChanger {
    public List<PoligonalModel> Models;
    public List<Scene> Scenes;
    public List<Flash> Flashes;
    public List<Camera> Cameras;
    private List<IModelChangerObserver> changerObservers; 

    public ModelStore(List<IModelChangerObserver> changerObservers, List<PoligonalModel> models, List<Scene> scene, List<Flash> Flashes, List<Camera> Cameras) throws Exception {
       
        this.changerObservers = changerObservers;
        Models = new ArrayList<>(null);
        Scenes = new ArrayList<>(null);
        Flashes = new ArrayList<>(null) ;
        Cameras = new ArrayList<>(null);

        Models.add(new PoligonalModel(null));
        Flashes.add(new Flash());
        Cameras.add(new Camera());
        Scenes.add(new Scene(0, Models, Flashes, Cameras));

        }

    public void NotifyChange(IModelChanger sender) {
    }

    public Scene getScena(int id) {
        for (int i = 0; i < Scenes.size(); i++) {

            if(Scenes.get(i).Id == id) {
                return Scenes.get(i);
            }   
        }
        return null;

    }    
    
}
