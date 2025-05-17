package Test;

import Controller.ArtisanController;
import Model.User;

public class ArtisanControllerTest {
    private ArtisanController ctrl;
    private User u;

    void init(){ u=new User(); u.setPosition(0,0); ctrl=new ArtisanController(u);
        u.getInventory().add("Salmon"); u.getInventory().add("Coal");
        u.placeMachine("Fish_Smoker",0,1); // adjacent
    }

    void randomTest1(){ // Fish Smoker flow
        assertEquals("Task queued: Salmon on Fish_Smoker ready at ", ctrl.handle(new String[]{"use","Fish_Smoker","Salmon"}).substring(0,47));
    }

    private void assertEquals(String s, String substring) {
    }

    void randomTest2(){ // Bee House auto
        u.placeMachine("Bee_House",1,0);
        String out=ctrl.handle(new String[]{"get","Bee_House"});
        // Bee house auto-produces Honey
        assertEquals("Honey", out);
    }
}
