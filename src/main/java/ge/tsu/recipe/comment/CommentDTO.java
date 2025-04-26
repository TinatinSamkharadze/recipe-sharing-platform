package ge.tsu.recipe.comment;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
public class CommentDTO {
    private Long id;
    private String author;
    private String text;
    private LocalDateTime createTime;
    private String prettyCreateTime;

    public static CommentDTO fromComment(Comment comment) {
        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(comment, dto);

        if (comment.getCreateTime() != null) {
            dto.setPrettyCreateTime(formatPrettyTime(comment.getCreateTime()));
        }

        return dto;
    }

    public void setPrettyCreateTime(String prettyTime) {
        this.prettyCreateTime = prettyTime;
    }

    private static String formatPrettyTime(LocalDateTime time) {
        if (time == null) {
            return "unknown time";
        }

        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(time, now);

        if (minutes < 1) {
            return "just now";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else if (minutes < 1440) { // Less than a day
            long hours = minutes / 60;
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        } else if (minutes < 10080) { // Less than a week
            long days = minutes / 1440;
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else {
            return time.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        }
    }
}