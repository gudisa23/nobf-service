package com.amind.att.service.webservice.domain;

import com.google.common.base.Supplier;

/**
 * IWeb Service Response
 *
 * @param <T>
 */
public interface IWebServiceResponse<T> extends Supplier<T> {

  /**
   * Get Entity ID
   *
   * @return
   */
  Long getEntityId();

  /**
   * Get Entity Name
   *
   * @return
   */
  String getEntityName();

  /**
   * Get Conversation ID
   *
   * @return
   */
  String getConversationId();

}
