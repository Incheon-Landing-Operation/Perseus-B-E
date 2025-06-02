package com.example.perseus.domain.constellation.ws;

import com.example.perseus.domain.constellation.entity.Constellation;
import com.example.perseus.domain.constellation.entity.vo.Point;
import com.example.perseus.domain.constellation.service.ConstellationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConstellationLocationHandler extends TextWebSocketHandler {
  private final Map<WebSocketSession, Point> sessionLocation = new ConcurrentHashMap();
  private final ConstellationService constellationService;

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("ws session connected: " + session.getId());
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    String message = textMessage.getPayload();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(message);

    if ("location".equals(jsonNode.get("type").asText())) {
      double lat = jsonNode.get("lat").asDouble();
      double lng = jsonNode.get("lng").asDouble();

      sessionLocation.put(session, new Point(lat, lng));
      List<Constellation> nearConstellation = constellationService.nearByLatAndLng(lat, lng);
      session.sendMessage(new TextMessage(objectMapper.writeValueAsString(nearConstellation)));
    }

  }
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    sessionLocation.remove(session);
  }
}
