package jire.packet.reflective;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jire.packet.PacketRepresentation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuildsPacket {

	Class<? extends PacketRepresentation> value();

}