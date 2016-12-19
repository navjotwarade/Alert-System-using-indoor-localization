/**
 * Filename: BaseMeasurementTypeAdapter.java (in org.redpin.android.json)
 * This file is part of the Redpin project.
 * <p/>
 * Redpin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * <p/>
 * Redpin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with Redpin. If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * (c) Copyright ETH Zurich, Pascal Brogle, Philipp Bolliger, 2010, ALL RIGHTS RESERVED.
 * <p/>
 * www.redpin.org
 */
package org.redpin.android.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.redpin.android.core.Measurement;

import java.lang.reflect.Type;

/**
 * adapter for specific org.redpin.base.core.* type (it is needed to get always
 * a org.repin.server.standalone.core.* instance after deserialization
 *
 * @see JsonSerializer
 * @see JsonDeserializer
 * @author Pascal Brogle (broglep@student.ethz.ch)
 *
 */
public class BaseMeasurementTypeAdapter implements
        JsonSerializer<org.redpin.base.core.Measurement>,
        JsonDeserializer<org.redpin.base.core.Measurement> {

    /**
     * @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
     */
    public JsonElement serialize(org.redpin.base.core.Measurement src,
                                 Type typeOfSrc, JsonSerializationContext context) {

        return context.serialize(src, Measurement.class);
    }

    /**
     * @see JsonDeserializer#deserialize(JsonElement, Type,
     *      JsonDeserializationContext)
     */
    public Measurement deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

        return context.deserialize(json, Measurement.class);
    }

}
