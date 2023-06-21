package edu.bupt.law.regulations.exception;

import edu.bupt.law.common.exception.notfound.NotFoundException;

public class RegulationNotFound extends NotFoundException {
    public RegulationNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RegulationNotFound(String id) {
        super("Regulation with id " + id + " not found");
    }
}
