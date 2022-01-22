package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}