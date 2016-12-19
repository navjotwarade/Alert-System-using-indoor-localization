/**
 * Filename: IRemoteEntityHome.java (in org.repin.android.net.home)
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
package org.redpin.android.net.home;

import org.redpin.android.net.Request;
import org.redpin.android.net.Response;

/**
 * Interface for RemoteEnityHomes
 *
 * @author Pascal Brogle (broglep@student.ethz.ch)
 *
 */
public interface IRemoteEntityHome {

    /**
     * This method is called after the {@link RemoteEntityHome} performed a
     * request and can be used to synchronize the local database
     *
     * @param request
     *            {@link Request} performed
     * @param response
     *            {@link Response} received
     * @param rHome
     *            {@link RemoteEntityHome} that calling this method
     */
    void onRequestPerformed(Request<?> request, Response<?> response,
                            RemoteEntityHome rHome);

}
