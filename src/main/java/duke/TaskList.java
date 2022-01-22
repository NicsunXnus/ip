package duke;
import duke.action.Action;

import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private final Storage storage;
    private final ArrayList<Action> list = new ArrayList<>();

    public TaskList() {
        storage = new Storage();
    }

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes the adding of the task
     * onto the list.
     * @param action the task
     */
    public void add(Action action) {
        list.add(action);
    }

    /**
     * Returns the list of tasks.
     * @return list
     */
    public ArrayList<Action> getList() {
        return list;
    }

    /**
     * Returns the size of the list.
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Sets one of the list's task's status to done
     * according to the given index from
     * the input.
     * @param taskNo index of task
     */
    public void setDone(int taskNo) {
        list.set(taskNo, list.get(taskNo).setDone());
    }

    /**
     * Unsets one of the list's task's status to undone
     * according to the given index from the input
     * @param taskNo index of task
     */
    public void setUnDone(int taskNo) {
        list.set(taskNo, list.get(taskNo).setUnDone());
    }

    /**
     * Returns one of the list's task based on the
     * given index from the input
     * @param taskNo index of task
     * @return a task
     */
    public Action getAction(int taskNo) {
        return list.get(taskNo);
    }

    /**
     * Returns one of the list's task based on the
     * given index from the input.Also removes it from
     * the list.
     * @param taskNo index of task
     * @return deleted task
     */
    public Action delete(int taskNo) {
        return list.remove(taskNo);
    }

    /**
     * Prints out the contents of the list
     * in a numeric notation.
     */
    public void listOut() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        if (list.isEmpty()) {
            System.out.println("Oh, you have nothing to do, how free you are!");
        } else {
            for (Action act : list) {
                System.out.println(count + "." + act);
                count++;
            }
        }
    }
}
