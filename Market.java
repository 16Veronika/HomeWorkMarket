import java.util.ArrayList;
import java.util.List;

public class Market implements MarketBehaviour, QueueBehaviour {
    private List<Actor> queue = new ArrayList<>();

    @Override
    public void takeInQueue(Actor actor) {
        queue.add(actor);
        System.out.println(actor.getName() + " Встал в очередь");
    }

    @Override
    public void takeOrders() {
        for (Actor actor : queue) {
            if (!actor.isMakeOrder())
                actor.isMakeOrder = true;
            System.out.println(actor.getName() + " Сделал заказ");
        }
    }

    @Override
    public void giveOrders() {
        for (Actor actor : queue) {
            if (actor.isMakeOrder())
                actor.isTakeOrder = true;
            System.out.println(actor.getName() + " Получил заказ");
        }
    }

    @Override
    public void releaseFromQueue() {
        List<Actor> releaseQueue = new ArrayList<>();
        for (Actor actor : queue) {
            if (actor.isMakeOrder()) {
                System.out.println(actor.getName() + " Вышел из очереди");
                releaseQueue.add(actor);
            }
        }
        releaseFromMarket(releaseQueue);
    }

    @Override
    public void acceptToMarket(Actor actor) {
        System.out.println(actor.getName() + " Попал в магазин");
        takeInQueue(actor);
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors) {
            System.out.println(actor.getName()+ " Вышел из магазина");
            queue.remove(actor);
        }
    }

    @Override
    public void update() {
        takeOrders();
        giveOrders();
        releaseFromQueue();
    }
}
