import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 */

/** Class is intended to represent static objects that
exsist on the game console.
*/
public class StaticGameEntity {
      private Node view;
      private Point2D position = new Point2D(0, 0);

      public StaticGameEntity(Node view) {
          this.view = view;
      }

      //Updating Node position through translation
      public void update() {
          view.setTranslateX(view.getTranslateX() + position.getX());
          view.setTranslateY(view.getTranslateY() + position.getY());
      }

      //Appropriate setters and getters
      public void setPosition(Point2D position) {
          this.position = position;
          /**Accessing X and Y coordinates come standard with Point2D,
          and can be accessed through getX(), getY(), setX(), and setY(). */
      }

      public Point2D getPosition() {
          return position;
      }

      public Node getView() {
          return view; //Privacy leak?
      }

  }
