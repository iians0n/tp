import java.nio.file.Path;

import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

/** Runs the tutorial's edit command against the unmodified team's baseline classes. */
public class TraceEdit {
    public static void main(String[] args) throws Exception {
        Path folder = Path.of(args[0]);
        var model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        var bookStorage = new JsonAddressBookStorage(folder.resolve("addressbook.json"));
        var storage = new StorageManager(bookStorage,
                new JsonUserPrefsStorage(folder.resolve("preferences.json")));
        var logic = new LogicManager(model, storage);
        System.out.println("Before: " + model.getFilteredPersonList().get(0));
        System.out.println(logic.execute("edit 1 n/Alice Yeoh").getFeedbackToUser());
        System.out.println("Saved: " + bookStorage.readAddressBook().orElseThrow().getPersonList().get(0));
    }
}
