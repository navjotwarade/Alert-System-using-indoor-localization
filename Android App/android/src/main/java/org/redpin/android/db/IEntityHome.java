/**
 * Filename: IEntityHome.java (in org.repin.android.db)
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
package org.redpin.android.db;

import java.util.List;

/**
 * Interface for all Entity Homes. An Entity Home provides all necessary
 * function to add, get, update and remove entities to/from the database
 *
 * @author Pascal Brogle (broglep@student.ethz.ch)
 *
 * @param <T>
 *            Element type
 * @param <ID>
 *            Primary key type (depending on database)
 */

public interface IEntityHome<T, ID> {

    T add(T e);

    List<T> add(List<T> list);

    T get(T e);

    T getById(ID id);

    List<T> get(List<T> list);

    List<T> getById(List<ID> ids);

    List<T> getAll();

    boolean update(T e);

    boolean update(List<T> list);

    boolean remove(T e);

    boolean removeById(ID id);

    boolean remove(List<T> list);

    boolean removeAll();

}
