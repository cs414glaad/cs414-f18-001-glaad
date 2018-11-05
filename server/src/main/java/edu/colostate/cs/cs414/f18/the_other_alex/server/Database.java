package edu.colostate.cs.cs414.f18.the_other_alex.server;

import edu.colostate.cs.cs414.f18.the_other_alex.model.User;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.GameService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.ModelService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.controllers.UserService;
import edu.colostate.cs.cs414.f18.the_other_alex.model.exceptions.UserNotFoundException;

import java.util.Observable;
import java.util.Observer;

public class Database implements Observer {
  public Database(ModelService modelService) {
    modelService.getUserService().addObserver(this);
    modelService.getGameService().addObserver(this);
  }

  public User searchUser(String query) {
    return null;
  }

  /**
   * Throws UserNotFoundException when user can't be found. Otherwise, returns the user. Should never return null.
   * @param username
   * @return
   */
  public User getUser(String username) throws UserNotFoundException {
    return null;
  }

  public User getUserByEmail(String email) {
    return null;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o.getClass() == UserService.class) {
      updateFromUserService((UserService)o);
    } else if (o.getClass() == GameService.class) {
      updateFromGameService((GameService)o);
    }
  }

  private void updateFromGameService(GameService gameService) {
    // TODO
  }

  private void updateFromUserService(UserService userService) {
    // TODO
  }
}
