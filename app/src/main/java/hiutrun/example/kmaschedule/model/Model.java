package hiutrun.example.kmaschedule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable {
        @SerializedName("error")
        @Expose
        private String error;
        @SerializedName("task")
        @Expose
        private String task;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("student_id")
        @Expose
        private String studentId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("class_name")
        @Expose
        private String className;
        @SerializedName("major")
        @Expose
        private String major;
        @SerializedName("schedule")
        @Expose
        private List<Schedule> schedule;

        public String getTask() {
                return task;
        }

        public String getError() {
                return error;
        }

        public Boolean getSuccess() {
                return success;
        }

        public String getStudentId() {
                return studentId;
        }

        public String getName() {
                return name;
        }

        public String getClassName() {
                return className;
        }

        public String getMajor() {
                return major;
        }

        public List<Schedule> getSchedule() {
                return schedule;
        }

}

