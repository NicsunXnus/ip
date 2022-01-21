import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class Duke {
    public static void listOut(ArrayList<Action> list) {
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

    public static void testForUnknown(Action act) throws DukeException {
        if (!act.known()) {
            throw new DukeException("What is this nonsense?");
        }
        if (act.getFull().equals("todo")) {
            throw new DukeException("Sorry, got to have something todo.");
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello!! I am Duke, your humble personal chatbot.\n" +
                "What can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        ArrayList<Action> store = new ArrayList<>();
        Action action = new Action(sc.nextLine());
        while (!action.isBye()) {
            try {
                testForUnknown(action);
            } catch (DukeException Dukex) {
                System.out.println(Dukex);
                action = new Action(sc.nextLine());
                continue;
            }
            if (action.isList()) {
                listOut(store);
                action = new Action(sc.nextLine());
                continue;
            }
            if (action.isMark()) {
                int index = Integer.parseInt(action.getBody());
                String statement;
                if (action.isUnmark()) {
                    store.set(index - 1, store.get(index - 1).setUnDone());
                    statement = "Ok, I have marked this task as not done yet:\n  ";
                } else {
                    store.set(index - 1, store.get(index - 1).setDone());
                    statement = "Nice! I have marked this task as done:\n  ";
                }
                System.out.println(statement + store.get(index - 1));
                action = new Action(sc.nextLine());
                continue;
            }
            if (action.isDelete()) {
                int index = Integer.parseInt(action.getBody());
                Action deleted = store.remove(index - 1);
                System.out.println("Noted. I have removed this task:\n  " +
                        deleted + "\nNow you have " + store.size() + " tasks in the list.");
                action = new Action(sc.nextLine());
                continue;
            }
            if (action.isDeadline()) {
                String[] sarr = action.getBody().split("/by");
                action = new Deadline(sarr[0], sarr[1]);
            }
            if (action.isEvent()) {
                String[] sarr = action.getBody().split("/at");
                action = new Event(sarr[0], sarr[1]);
            }
            if (action.isTodo()) {
                action = new Todo(action.getBody());
            }
            store.add(action);
            System.out.println("Got it. I have added this task:\n  " + action +
                    "\nNow you have " + store.size() + " tasks in the list.");
            action = new Action(sc.nextLine());
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}