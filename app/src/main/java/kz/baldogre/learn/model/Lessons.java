package kz.baldogre.learn.model;

import java.util.ArrayList;
import java.util.List;

public final class Lessons {
    private Lessons() {

    }

    public static List<Course> getAllLessons() {
        List<Course> courses = new ArrayList<>();
        int lessonId = 0;
        int courseId = 0;

        courses.add(getFirstCourse(lessonId, courseId));
        lessonId = courses.get(0).getLessons().size();
        courseId++;
        courses.add(getSecondCourse(lessonId, courseId));
        return courses;
    }

    private static Course getSecondCourse(int lessonId, int courseId) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Course course = new Course();
        course.setId(courseId);
        course.setDescription("<b>2 Урок (упражнения на верхнюю часть лица)\n</b>" +
                "<b>2.1 Расслабление лобной мышцы\n</b>" +
                "Расслабление лобной мышцы. Результат: Расслабление и вытяжение мышц в естественную длину.\n" +
                "(См. мышца №1)\n\n" +
                "<b>2.2 Укрепление лобной мышцы\n</b>" +
                "Упражнение на лобную мышцу (см. мышца №1)\n" +
                "Результат: разглаживание горизонтальных складок и морщин лба.\n\n" +
                "<b>2.3 Укрепление мышцы гордецов\n</b>" +
                "Упражнение на мышцу «гордецов» (см. мышцу №3).\n" +
                "Результат: разглаживание вертикальных складок межбровья и поперечной складки переносицы.\n\n" +
                "<b>2.4 Расслабление круговой мышцы глаза\n</b>" +
                "Упражнение на круговые мышцы глаз (см. мышцу №2).\n" +
                "Результат: расслабление круговой мышцы глаз.\n\n" +
                "<b>2.5 Укрепление на верхнее веко\n</b>" +
                "Упражнение на верхнюю часть век круговой мышцы глаз.\n" +
                "Результат: подтяжка верхних век.\n\n" +
                "<b>2.6 Укрепление нижнего века\n</b>" +
                "Упражнение на нижнюю часть век круговой мышцы глаз.\n" +
                "Результат: укрепления нижнего века.");

        Lesson lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("NmFhSFHfQWk");
        lesson.setDescription("<b>2.1 Расслабление лобной мышцы\n</b>" +
                "Расслабление лобной мышцы. Результат: Расслабление и вытяжение мышц в естественную длину.\n" +
                "(См. мышца №1)\n\n");
        lesson.setCourseId(courseId);
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("OhLavbEdqmk");
        lesson.setDescription("<b>2.2 Укрепление лобной мышцы\n</b>" +
                "Упражнение на лобную мышцу (см. мышца №1)\n" +
                "Результат: разглаживание горизонтальных складок и морщин лба.\n\n");
        lessons.add(lesson);
        lesson.setCourseId(courseId);

        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("VqaerEP6ssI");
        lesson.setDescription("<b>2.3 Укрепление мышцы гордецов\n</b>" +
                "Упражнение на мышцу «гордецов» (см. мышцу №3).\n" +
                "Результат: разглаживание вертикальных складок межбровья и поперечной складки переносицы.\n\n");
        lesson.setCourseId(courseId);
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("NmFhSFHfQWk");
        lesson.setDescription("<b>2.4 Расслабление круговой мышцы глаза\n</b>" +
                "Упражнение на круговые мышцы глаз (см. мышцу №2).\n" +
                "Результат: расслабление круговой мышцы глаз.\n\n");
        lesson.setCourseId(courseId);
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("OhLavbEdqmk");
        lesson.setDescription("<b>2.5 Укрепление на верхнее веко\n</b>" +
                "Упражнение на верхнюю часть век круговой мышцы глаз.\n" +
                "Результат: подтяжка верхних век.\n\n");
        lessons.add(lesson);
        lesson.setCourseId(courseId);

        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("VqaerEP6ssI");
        lesson.setDescription("<b>2.6 Укрепление нижнего века\n</b>" +
                "Упражнение на нижнюю часть век круговой мышцы глаз.\n" +
                "Результат: укрепления нижнего века.");
        lesson.setCourseId(courseId);
        lessons.add(lesson);
        course.setLessons(lessons);
        return course;
    }

    private static Course getFirstCourse(int lessonId, int courseId) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Course course = new Course();
        course.setId(courseId);
        course.setDescription("<b>1 Урок.</b>\n\n" +
                "        <b>1.1 Массаж апоневроза</b>\n\n" +
                "Старение лица начинается с изменений, которые происходят в надчерепной мышце, а точнее в" +
                "апоневротическом шлеме. Апоневротический шлем с возрастом имеет способность склеиваться с" +
                "черепом и мигрировать к центру лица и создает горизонтальные складки на лбу, также является" +
                "одной из причин носогубной складки и ухудшение овала лица. Проработка апоневротического" +
                "шлема является основой всей гимнастики.\n" +
                "Результат: Перестает беспокоить мигрень, глазное давление, появляется ощущение легкости.\n\n" +
                "\n" +
                "<b>1.2 Массаж височной части</b>\n\n" +
                "Миграция апоневротического шлема приводит к движению височной мышцы. За счет этой\n" +
                "миграции происходит нависания верхнего века и образование межбровной складки. Проработка\n" +
                "височной мышцы усилит действие упражнений на верхнюю, среднюю и нижнюю части лица.\n\n" +
                "<b>1.3 Массаж ушей</b>\n" +
                "Массаж — ушей - это очень важный момент, так как с возрастом, уши меняют свое место посадки\n" +
                "и образуют множество морщин перед ушами и за ушами. Делаем массаж ушей до покраснения,\n" +
                "примерно, 1 минуту.\n\n");
        Lesson lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("NmFhSFHfQWk");
        lesson.setDescription("<b>1 Урок.</b>\n\n" +
                "        <b>1.1 Массаж апоневроза</b>\n\n" +
                "Старение лица начинается с изменений, которые происходят в надчерепной мышце, а точнее в" +
                "апоневротическом шлеме. Апоневротический шлем с возрастом имеет способность склеиваться с" +
                "черепом и мигрировать к центру лица и создает горизонтальные складки на лбу, также является" +
                "одной из причин носогубной складки и ухудшение овала лица. Проработка апоневротического" +
                "шлема является основой всей гимнастики.\n" +
                "Результат: Перестает беспокоить мигрень, глазное давление, появляется ощущение легкости.\n\n");
        lesson.setCourseId(courseId);
        lessons.add(lesson);
        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("OhLavbEdqmk");
        lesson.setDescription("<b>1.2 Массаж височной части</b>\n\n" +
                "Миграция апоневротического шлема приводит к движению височной мышцы. За счет этой\n" +
                "миграции происходит нависания верхнего века и образование межбровной складки. Проработка\n" +
                "височной мышцы усилит действие упражнений на верхнюю, среднюю и нижнюю части лица.\n\n");
        lessons.add(lesson);
        lesson.setCourseId(courseId);
        lesson = new Lesson();
        lesson.setId(lessonId++);
        lesson.setLink("VqaerEP6ssI");
        lesson.setDescription("<b>1.3 Массаж ушей</b>\n" +
                "Массаж — ушей - это очень важный момент, так как с возрастом, уши меняют свое место посадки\n" +
                "и образуют множество морщин перед ушами и за ушами. Делаем массаж ушей до покраснения,\n" +
                "примерно, 1 минуту.\n\n");
        lesson.setCourseId(courseId);
        lessons.add(lesson);
        course.setLessons(lessons);
        return course;
    }
}
