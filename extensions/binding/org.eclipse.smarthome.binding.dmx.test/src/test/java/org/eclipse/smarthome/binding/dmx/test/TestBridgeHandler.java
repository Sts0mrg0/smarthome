/**
 * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.binding.dmx.test;

import static org.eclipse.smarthome.binding.dmx.DmxBindingConstants.BINDING_ID;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.binding.dmx.internal.DmxBridgeHandler;
import org.eclipse.smarthome.binding.dmx.internal.multiverse.Universe;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link TestBridgeHandler} is only for testing
 *
 * @author Jan N. Klug - Initial contribution
 */

public class TestBridgeHandler extends DmxBridgeHandler {
    public final static ThingTypeUID THING_TYPE_TEST_BRIDGE = new ThingTypeUID(BINDING_ID, "test-bridge");
    public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES = Collections.singleton(THING_TYPE_TEST_BRIDGE);
    public static final int MIN_UNIVERSE_ID = 0;
    public static final int MAX_UNIVERSE_ID = 0;

    private final Logger logger = LoggerFactory.getLogger(TestBridgeHandler.class);

    public TestBridgeHandler(Bridge testBridge) {
        super(testBridge);
    }

    @Override
    protected void openConnection() {

    }

    @Override
    protected void closeConnection() {

    }

    @Override
    protected void sendDmxData() {

    }

    @Override
    protected void updateConfiguration() {
        universe = new Universe(MIN_UNIVERSE_ID);
        universe.setRefreshTime(0);

        super.updateConfiguration();

        updateStatus(ThingStatus.ONLINE, ThingStatusDetail.NONE);

        logger.debug("updated configuration for Test bridge {}", this.thing.getUID());
    }

    @Override
    public void initialize() {
        logger.debug("initializing Test bridge {}", this.thing.getUID());

        updateConfiguration();
    }

    /**
     * calc buffer for timestamp after timespam
     *
     * @param time UNIX timestamp of calculation time
     *
     * @return new timestamp
     */
    public long calcBuffer(long time, long timespan) {
        universe.calculateBuffer(time);
        universe.calculateBuffer(time + timespan);
        logger.debug("calculated buffer for {} to {} (difference {})", time, time + timespan, timespan);
        return time + timespan;
    }

    /**
     * update bridge status manually
     *
     * @param status a ThingStatus to set the bridge to
     */
    public void updateBridgeStatus(ThingStatus status) {
        updateStatus(status);
    }
}
