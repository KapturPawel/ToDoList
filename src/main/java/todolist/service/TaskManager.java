package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.model.Task;
import todolist.repository.TaskRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskManager implements ITaskManager {

    @Autowired
    public TaskRepository taskRepository;

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void addNewTask(Task task, Long userId) {
        task.setDescription(task.getDescription());
        task.setCreatedAt(getDate());
        task.setArchived(false);
        task.setUserId(userId);
        taskRepository.save(task);
    }

    public void editTask(String description, Long id){
        Task task = taskRepository.findOne(id);
        task.setDescription(description);
        task.setUpdatedAt(getDate());
        taskRepository.save(task);
    }

    public void makeDone(Long id){
        Task task = taskRepository.findOne(id);
        if (task.getDoneAt() == null) {
            task.setDoneAt(getDate());
        }
        taskRepository.save(task);
    }

    public void archiveTask(Long id){
        Task task = taskRepository.findOne(id);
        task.setArchived(!task.isArchived());
        taskRepository.save(task);
    }

    public List<Task> getArchived(boolean archived, Long userId){
        return taskRepository.findByIsArchivedAndUserId(archived, userId);
    }

    public Task getById(Long id){
        return taskRepository.findOne(id);
    }
}
