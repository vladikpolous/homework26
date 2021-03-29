package ua.com.alevel;

import org.bson.conversions.Bson;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.model.Job;
import ua.com.alevel.model.User;
import com.mongodb.client.model.Filters;
import java.util.List;


public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // Task number 1
        Bson filterByTitleAndAge = Filters.and(Filters.eq("title","Java Junior Dev"),Filters.lt("age",21));
        List<User> developers = userDao.findAllByFilter(filterByTitleAndAge);
        System.out.println(developers);

        // Task number 2
        Bson filterByTitle = Filters.regex("title","Junior");
        List<User> developersByTitle = userDao.findAllByFilter(filterByTitle);
        System.out.println(developersByTitle);

        // Task number 3
        Bson filterByJob = Filters.elemMatch("jobs",Filters.eq("organization","Amazon"));
        List<User> developersByJob = userDao.findAllByFilter(filterByJob);
        System.out.println(developersByJob);

        // Task number 4
        Bson filterByQuantityOfJobs = Filters.exists("jobs.3");
        List<User> developersByQuantityOfJobs = userDao.findAllByFilter(filterByQuantityOfJobs);
        System.out.println(developersByQuantityOfJobs);
    }
    private static User createUser(int age, String fullName, String title, List<Job> jobs) {
        User user = new User();
        user.setAge(age);
        user.setFullName(fullName);
        user.setTitle(title);
        user.setJobs(jobs);
        return user;
    }
}
