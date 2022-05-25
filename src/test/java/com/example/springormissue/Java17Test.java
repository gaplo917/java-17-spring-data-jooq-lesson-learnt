package com.example.springormissue;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Calendar.*;

@SuppressWarnings("ALL")
public class Java17Test {

  @Test
  void java_9_try_with_resources_with_close() {
    var java8 = (Supplier<Void>)() -> {
      BufferedReader br = new BufferedReader(new StringReader("Hello world example!"));
      try {
        System.out.println(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return null;
    };

    var java9 = (Supplier<Void>)() -> {
      try (
          BufferedReader br = new BufferedReader(new StringReader("Hello world example3!"))
      ) {
        System.out.println(br.readLine());
      } catch (IOException e) {
        System.out.println("Error happened!");
      }
      return null;
    };

  }

  @Test
  void java9_new_api() {

    var java8 = (Supplier<Void>)() -> {
      List<Integer> list = new ArrayList<>();
      list.add(1);
      list.add(2);

      List<Integer> list2 = Arrays.asList(1,2);

      return null;
    };

    var java9 = (Supplier<Void>)() -> {
      List.of(1,2);
      return null;
    };



  }

  @Test
  void java14_local_variable_type() {

    var java8 = (Supplier<Void>)() -> {
      String stringValue = "abc";
      int interValue = 1;
      long longValue = 1L;
      Supplier<Void> fn = () -> {
        var test = 1;
        return null;
      };
      List<Integer> intList = new ArrayList<>();

      List<String> strList = intList
          .stream()
          .map(it -> it + stringValue)
          .collect(Collectors.toList());

      return null;
    };

    var java14 = (Supplier<Void>)() -> {
      var stringValue = "abc";
      var interValue = 1;
      var longValue = 1L;
      var fn = (Supplier<Void>)() -> {
        var test = 1;
        return null;
      };
      final var intList = new ArrayList<Integer>();

      var strList = intList
          .stream()
          .map(it -> it + stringValue)
          .collect(Collectors.toList());
      return null;
    };

  }

  @Test
  void java16_new_api() {
    var list = new ArrayList<Integer>();

    var java8 = (Supplier<Void>)() -> {
      list
          .stream()
          .map(it -> it + "foo")
          .collect(Collectors.toList());
      return null;
    };

    var java16 = (Supplier<Void>)() -> {
      list
          .stream()
          .map(it -> it + "foo")
          .toList();
      return null;
    };
  }

  @Test
  void java14_switch_expression() {
    var java8 = (Supplier<Void>)() -> {
      Month month = Month.APRIL;
      int days = 0;

      if (List.of(JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER).contains(month)) {
        days = 31;
      }
      return null;
    };
    var java14 = (Supplier<Void>)() -> {
      Month month = Month.APRIL;
      var days = switch (month) {
        case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
        case FEBRUARY -> 28;
        case APRIL, JUNE, SEPTEMBER, NOVEMBER -> {
          System.out.println(month);
          yield 20;
        }
        default -> throw new IllegalArgumentException("no match month");
      };
      return null;
    };
  }

  @Test
  void java15_text_block() {
    var java8 = (Supplier<Void>)() -> {
      String longString = "INSERT INTO demo_reaction" +
          " (id, owner_id, post_id, comment_id, reaction_type) " +
          "VALUES (" +
          "nextval('hibernate_sequence')," +
          ":ownerId," +
          ":postId," +
          ":commentId," +
          ":reactionType" +
          ")RETURNING *;";

      return null;
    };

    var java15 = (Supplier<Void>)() -> {
      String longString = """
          INSERT INTO demo_reaction (
            id,
            owner_id,
            post_id,
            comment_id,
            reaction_type
          ) VALUES (
            nextval('hibernate_sequence'),
            :ownerId,
            :postId,
            :commentId,
            :reactionType
           )
           RETURNING *;
          """;
      return null;
    };
  }

  @Test
  void java16_pattern_match() {
    var java8 = (Supplier<Void>)() -> {
      Object something = 1;
      if (something instanceof Long) {
        var result = (Long)something + 1;
      } else if (something instanceof BigDecimal) {
        var result = ((BigDecimal)something).add(BigDecimal.ONE);
      } else throw new IllegalArgumentException("type mismatch");
      return null;
    };

    var java16 = (Supplier<Void>)() -> {
      Object something = 1;
      if (something instanceof Long longValue) {
        var result = longValue + 1;
      } else if (something instanceof BigDecimal bigDecimal) {
        var result = bigDecimal.add(BigDecimal.ONE);
      } else throw new IllegalArgumentException("type mismatch");
      return null;
    };
  }

  @Test
  void java16_records() {
    record Something(int id, String username) {};
    var sth = new Something(1, "Gary");
  }

  // java 17 sealed class
  final class SoftwareArchitecture extends SoftwareEngineering {}
  final class Infrastructure extends SoftwareEngineering {}

  // add rules
  sealed class SoftwareEngineering permits DesignPattern, Infrastructure, SoftwareArchitecture {}

  // need to specific non-sealed class
  non-sealed class DesignPattern extends SoftwareEngineering {}

  // or need to specific final class
  final class Singleton extends DesignPattern {};
  final class FactoryPattern extends DesignPattern {};

  // (open class) does not work
//  class DesignPattern extends SoftwareEngineering {}

  public interface Toxic {

    void eatTamJai();

    void surfLihkg();

    int meetWithPeople();

    /**
     * Assume 0.1% chance can convert girl friend from normal friends
     * @return
     * @since java 8
     */
    default int makeGirlFriends(){
      // can use private method in interface
      return makeFriends() / 1000;
    }

    /**
     * Assume 0.1% chance convert make friends
     * @return
     * @since java 17
     */
    private int makeFriends(){
      return meetWithPeople() / 1000;
    }
  }
}
