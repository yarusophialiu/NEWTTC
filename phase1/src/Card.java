public class Card {
    private boolean isSuspended = false;
    private int id;


    void reverseSuspended(){
        this.isSuspended = !this.isSuspended;
    }

    int getId(){
        return this.id;
    }
}
