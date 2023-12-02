package Interfaces;

import Http.Request;
import Http.Response;

@FunctionalInterface
  public interface Action {
    Response call(Request request, Response response) throws Exception;
  }