/**
 * $RCSfile$
 * $Revision$
 * $Date$
 *
 * Copyright 2004 Jive Software.
 *
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmpp.component;

import org.xmpp.packet.Packet;

/**
 * Manages components.
 *
 * @see Component
 * @author Matt Tucker
 */
public interface ComponentManager {

    /**
     * Adds a component. The {@link Component#initialize(org.xmpp.packet.JID, ComponentManager)}
     * method will be called on the component. The subdomain specifies the address of
     * the component on a server. For example, if the subdomain is "test" and the XMPP
     * server is at "example.com", then the component's address would be "test.example.com".
     *
     * @param subdomain the subdomain of the component's address.
     * @param component the component.
     */
    public void addComponent(String subdomain, Component component) throws ComponentException;

    /**
     * Removes a component. The {@link Component#shutdown} method will be called on the
     * component.
     *
     * @param subdomain the subdomain of the component's address.
     */
    public void removeComponent(String subdomain) throws ComponentException;

    /**
     * Sends a packet to the XMPP server. The "from" value of the packet must be in
     * the domain name of the component. For example, if the component has a domain
     * of "test.example.com", then "user@test.example.com" would be a valid "from"
     * value.
     *
     * @param component the component sending the packet.
     * @param packet the packet to send.
     */
    public void sendPacket(Component component, Packet packet) throws ComponentException;

    /**
     * Returns a property value specified by name. Properties can be used by
     * components to store configuration data. It is recommended that each
     * component qualify property names to prevent overlap. For example a
     * component that broadcasts messages to groups of users, might prepend
     * all property names it uses with "broadcast.".
     *
     * @param name the property name.
     * @return the property value.
     */
    public String getProperty(String name);

    /**
     * Sets a property value. Properties can be used by
     * components to store configuration data. It is recommended that each
     * component qualify property names to prevent overlap. For example a
     * component that broadcasts messages to groups of users, might prepend
     * all property names it uses with "broadcast.".
     *
     * @param name the property name.
     * @param value the property value.
     */
    public void setProperty(String name, String value);

    /**
     * Returns true if components managed by this component manager are external
     * components connected to the server over a network connection. Otherwise,
     * the components are internal to the server.
     *
     * @return true if the managed components are external components.
     */
    public boolean isExternalMode();
}