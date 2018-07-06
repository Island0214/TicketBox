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
        s.setType("音乐会");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2018-09-14 19:30:00");
        s.setTime(date);

        s.setSchedule("“致爱丽丝”经典浪漫钢琴名曲音乐会");
        s.setIntro("曲目丰富多样，经典音乐一网打尽！\n" +
                "大师精心编配，呈现不一样的经典！\n" +
                "特聘编曲：Jan Valta  亚安·瓦尔塔（捷克著名作曲家）出生在音乐世家，曾任捷克爱乐乐团小提琴演奏家。后因热爱音乐创作，辞去捷克爱乐演奏家职位专心创作音乐，目前已经成为欧洲著名的青年作曲家，为众多知名乐团、游戏、电影创作和改编音乐。\n" +
                " \n" +
                "浪漫主义和古典主义于一身的\"乐圣\"-贝多芬\n" +
                "贝多芬（Ludwig van Beethoven），生于1770年12月17日，逝世于1827年3月26日。德国著名的音乐家，维也纳古典乐派代表人物之一。贝多芬在音乐史的地位是极其突出的，他不仅是古典主义风格的集大成者，同时又是浪漫主义风格的开创者。作为音乐大师，贝多芬对艺术歌曲同样予以相当程度的关注，他是德国艺术歌曲创造的先驱，毕生作有钢琴伴奏的艺术歌曲六十多首，他的艺术歌曲以其丰富的表现手法和形式来展现，表达属于全人类的情感。在艺术歌曲的领域里取得了非凡成就。\n" +
                "多首经典浪漫钢琴名曲 一次欣赏久久难忘\n" +
                "享誉\"乐圣\"之称的贝多芬谱写的世界名曲《致爱丽丝》可谓是世界古典乐曲中的经典之作，旋律曼妙，动人。世界人民对它的熟稔程度大到各个古典音乐演奏会，小到街上的每一角落，无处不在。当旋律响起的时候，焦躁的心渐渐沉浮下来，脑海中当即浮现一个女孩身影，感受着她对这世界洒下的爱和关怀，享受着旋律带给心灵的片刻安宁。\n" +
                "本场音乐会不仅能够完美的诠释《致爱丽丝》，爱乐汇轻音乐团的音乐家们将为乐迷现场演奏经过捷克作曲大师重新编配浪漫经典曲目，这台音乐会在曲目的选择上丰富多彩：除了经典曲目《致爱丽丝》、莫扎特的《土耳其进行曲》、勃拉姆斯的《匈牙利舞曲》，一举囊括了不同时期、各种题材、风格迥异的经典音乐，还包括了班得瑞的《童年的回忆》、经典韩剧\"蓝色生死恋\"插曲《理由》，中国经典曲目《彩云追月》、《瑶族舞曲》。马克西姆的《静如止水》这首歌曲整体的基调是十分平和的，听着曲子，仿佛自己是在望着一潭深水，这安静的水面，似乎有蜻蜓落上去就会出现点点的涟漪……\n" +
                "关于 爱乐汇轻音乐团\n" +
                "爱乐汇轻音乐团（Music Fans Classical--MFC乐团）集古典与时尚于一身，既保留了古典的风格，又增添了当下的流行元素。是当前国内演出市场著名的跨界表演乐团。乐团成员凭借娴熟精湛的演奏技巧，丰富多样的舞台经验以及天衣无缝的默契配合，自创立以来全国演出数百场，收获良多赞誉与爱戴。欲了解更多及演员信息请通过微博或微信公众号搜索关注@爱乐汇轻音乐团 。\n" +
                "关于 爱乐汇（MUSICFANS）\n" +
                "爱乐汇，演出运营品牌机构，专注于制作及引进老百姓即喜爱又消费得起的现场艺术，精心策划运营每年近千场剧院演出，覆盖全国近百万人次观众。爱乐汇拥有演出领域丰富从业经验的运营团队，通过与艺术家和乐团的深度合作，完成作品的生产创作。在策划上避免曲高和寡、华而不实，在运营上力争专业执着、精益求精。通过无数次体验与论证，最终呈现好听又好看的演出剧目，为每一位走进剧院的观众提供丰富实际的艺术享受。\n" +
                "演出曲目\n" +
                "上半场\n" +
                "1.童年的回忆 Childhood Memory\n" +
                "2.土耳其进行曲 Turkish March\n" +
                "3.遗忘 Oblivion  \n" +
                "4.波萨诺瓦舞曲 Bossa Nova\n" +
                "5.中国故事 A Chinese Tall Story  \n" +
                "6.太阳照常升起 The Sun Also Rises  \n" +
                "7.静如止水 Still Waters  \n" +
                "8.匈牙利舞曲第5号 Hungarian Dance No.5\n" +
                "下半场\n" +
                "9.致爱丽丝 For Elise\n" +
                "10.眼泪 Tears\n" +
                "11.理由 Reason\n" +
                "12.曼波舞曲 Mambo\n" +
                "13.彩云追月 Clouds Chasing the Moon  \n" +
                "14.瑶族舞曲 Dance Of The Yao\n" +
                "15.雨中印记 Kiss the rain\n" +
                "16.天空之城 Castle in the Sky\n" +
                "备注：最终曲目以现场为准。");
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
