package edu.colostate.cs.cs414.f18.the_other_alex.model;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
