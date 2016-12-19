
package org.redpin.base.core.measure;


import org.redpin.base.core.Types;


public class BluetoothFingerprint {


	/* attributes */
	protected int mapid ;
	protected int locid ;
	protected int rssi; // see
	protected String bluetoothaddress = "";
	protected int mapXCord;
	protected int mapYCord;
	
	/* **************** Getter and Setter Methods **************** */

	/**
	 * @return the friendlyName
	 */
	public int getmapid() {
		return mapid;
	}

	/**
	 * @param friendlyName
	 *            the friendlyName to set
	 */
	public void setlocid(int locid) {
		this.locid = locid;
	}

	public int getlocid() {
		return locid;
	}

	/**
	 * @param friendlyName
	 *            the friendlyName to set
	 */
	public void setmapid(int mapid) {
		this.mapid = mapid;
	}
	/**
	 * @return the bluetoothAddress
	 */
	public String getBluetoothAddress() {
		return bluetoothaddress;
	}

	public int getBluetoothrssi() {
		return rssi;
	}

	public void setBluetoothrssi(int rssi) {
		this.rssi = rssi;
	}
	/**
	 * @param bluetoothAddress
	 *            the bluetoothAddress to set
	 */
	public void setBluetoothAddress(String bluetoothAddress) {
		this.bluetoothaddress = bluetoothAddress;
	}



	/**
	 * @return the majorDeviceClass
	 */
	public int getmapXCord() {
		return mapXCord;
	}

	/**
	 * @param majorDeviceClass
	 *            the majorDeviceClass to set
	 */
	public void setmapXCord(int mapXCord) {
		this.mapXCord = mapXCord;
	}

	/**
	 * @return the minorDeviceClass
	 */
	public int getmapYCord() {
		return mapYCord;
	}

	/**
	 * @param minorDeviceClass
	 *            the minorDeviceClass to set
	 */
	public void setmapYCord(int mapYCord) {
		this.mapYCord = mapYCord;
	}

	public String getType() {
		return Types.BLUETOOTH;
	}
	
	/*
	 * removed due to conflicts: what is id needed for?
	 *
	public String getId() {
		return bluetoothAddress;
	}
	*/
	
	
	/**
	 * Returns Bluetooth Friendly Name
	 */
/*	public String getHumanReadableName() {
		//return friendlyName;
	}
*/
	public String toString() {
		return super.toString() + ": " + "locid" + "=" + locid
				+ "|" + Types.BLUETOOTH_ADDRESS + "=" + bluetoothaddress + "|"
				+ "mapXCord" + "=" + mapXCord + "|"
				+ "mapYCord" + "=" + mapYCord;
	}

	
}
