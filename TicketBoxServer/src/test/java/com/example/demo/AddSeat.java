package com.example.demo;

import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.Schedule;
import com.example.model.Seat;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author qianzhihao
 * @version 2018/7/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddSeat {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    public void addSeat() throws ParseException, IOException {
        Schedule s = new Schedule();
        s.setStatus(0);
        s.setCity("南京");
        s.setTourId(0);
        Random random = new Random();
        s.setHotValue(random.nextInt(100));
        s.setVenue(1111132);
        s.setType("戏剧");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2018-09-02 19:30:00");
        s.setTime(date);

        s.setSchedule("开心麻花爆笑舞台剧《婿事待发》");
        s.setIntro("美国留学的姐姐年近30仍未找到归宿，在一次爬山途中认识了年近七旬的Andy，二人彼此倾心。但随着二人婚事临近，男友提出希望拜访女方父母，而姐姐深知，男友的年纪对于自己观念传统的家庭来说是绝对的禁忌。\n" +
                "为了掩饰男友的身份，姐姐请求妹妹编造谎言企图瞒过家人，随着各种不同身份的人物接连出现，麻烦越来越大，撒的谎也越来越多……\n" +
                "\n" +
                "\n" +
                "\n" +
                "好看在哪？\n" +
                "\n" +
                "1、竟然可以这么搞？\n" +
                "踩中准女婿上门雷区！\n" +
                "\n" +
                "尬聊、尬舞、尬英文\n" +
                "错误的见家长方式这都有！\n" +
                "岳父花式“折磨”准女婿，姐妹花为隐瞒真相狂放大招\n" +
                "搞笑，我们全家人都是认真的！\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "2、竟然可以这么乱？\n" +
                "超级家庭版“无间道”正在上演！\n" +
                "\n" +
                "如何让父母接受高龄男友？\n" +
                "冒名顶替只是开胃菜\n" +
                "女扮男，老装嫩，误会、巧合、计谋层层叠加\n" +
                "4个人对彼此身份有13种误解\n" +
                "每3句话就有1句谎言！\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "3、竟然可以这么暖？\n" +
                "老骑士为爱情冲破一切禁锢！\n" +
                "\n" +
                "年近古稀的总裁和女留学生相爱\n" +
                "注定悲剧结局？\n" +
                "爱可以超越门第，性别，甚至种族……更何况是年龄？\n" +
                "永恒的爱情谜题，从未被如此温情破解\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "4、竟然可以这么吊？\n" +
                "刘坤最新“伦理”力作！\n" +
                "\n" +
                "《夏洛特烦恼》“陈凯哥”刘坤执导\n" +
                "精细的调度，让家庭故事变得和悬疑片一样紧张好玩\n" +
                "别说闹剧慌了\n" +
                "环环相扣的佳构剧等你鉴赏~\n" +
                "\n" +
                "\n" +
                "\n" +
                "导演、编剧：刘坤\n" +
                "毕业于中国传媒大学，开心麻花签约导演、编剧、演员。\n" +
                "在影视方面，因非常具有记忆度的外型，被誉为“万中无一的演员”，参演过《欢喜密探》《红楼梦》《刀锋青春》《建国大业》等多部优秀作品。他在电影《夏洛特烦恼》饰演的“大哥陈凯”，给观众留下了极其深刻的印象。\n" +
                "在剧场，他参演过开心麻花《羞羞的铁拳》《小丑爱美丽》《乌龙山伯爵》《李茶的姑妈》等多部舞台剧，刘坤的表演精准、细腻、感染力极强，有着自己独特的节奏。出色的表演功力使他观众缘爆表，不少人专门为了他走进剧场。\n" +
                "另外，他导演的喜剧《蒙圈天使》和开心麻花邀请剧目《如果，我不是我》，都备受赞誉，口碑极佳。");
        s.setArtist("开心麻花团队");

        s.setPoster("");
        scheduleRepository.save(s);

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 15; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "A区", i, j, 1280, 0));
            }
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 15; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "B区", i, j, 980, 0));
            }
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 7; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "C1区", i, j, 680, 0));
            }
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 7; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "C2区", i, j, 680, 0));
            }
        }
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 15; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "D区", i, j, 380, 0));
            }
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 7; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "E1区", i, j, 280, 0));
            }
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 7; j++) {
                seatRepository.save(new Seat(s.getSchedule_id(), "E2区", i, j, 280, 0));
            }
        }
    }

    public static String encodeToString(String imagePath) throws IOException {
        String type = StringUtils.substring(imagePath, imagePath.lastIndexOf(".") + 1);
        BufferedImage image = ImageIO.read(new File(imagePath));
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

}
