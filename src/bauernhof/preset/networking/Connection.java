package bauernhof.preset.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import bauernhof.preset.Move;

/**
 * Class for sending and receiving simple data types to and from a socket.
 *
 */
class Connection {
	/**
	 * the socket
	 */
	private final Socket socket;
	/**
	 * the input for reading/receiving data
	 */
	private final DataInputStream input;
	/**
	 * the output for writing/sending data
	 */
	private final DataOutputStream output;


	/**
	 * Constructor.
	 *
	 * @param socket the socket
	 * @throws IOException networking error
	 */
	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		this.input = new DataInputStream(socket.getInputStream());
		this.output = new DataOutputStream(socket.getOutputStream());
	}


	/**
	 * Get remote socket address.
	 *
	 * @return remote socket address
	 */
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();
	}

	/**
	 * Is connection open?
	 *
	 * @return true if open, false otherwise
	 */
	public boolean isOpen() {
		return !socket.isClosed() && socket.isConnected();
	}





	/**
	 * Close connection.
	 *
	 * @throws IOException networking error
	 */
	public void close() throws IOException {
		if (socket.isClosed() && socket != null) {
			return;
		}
		input.close();
		output.close();
		if (socket != null) {
			socket.close();
		}
	}

	/**
	 * Main send function.
	 *
	 * @param data the data to send
	 * @throws IOException networking error
	 */
	public void send(String data) throws IOException {
		if (data == null) {
			output.writeBoolean(true);
		} else {
			output.writeBoolean(false);
			output.writeUTF(data);
		}
	}

	/**
	 * Main receive function.
	 *
	 * @return received data
	 * @throws IOException networking error
	 */
	public String receive() throws IOException {
		boolean isnull = input.readBoolean();


		if (isnull) {
			return null;
		} else {
			String data = input.readUTF();
			return data;
		}
	}

	/**
	 * send data
	 *
	 * @param data the data
	 * @throws IOException networking error
	 */
	public void send(Integer data) throws IOException {
		send(String.valueOf(data));
	}

	/**
	 * receive data
	 *
	 * @return the data
	 * @throws IOException networking data
	 */
	public Integer receiveInteger() throws IOException {
		return Integer.valueOf(receive());
	}

	/**
	 * send data
	 *
	 * @param data the data
	 * @throws IOException networking error
	 */
	public void send(Boolean data) throws IOException {
		send(String.valueOf(data));
	}

	/**
	 * receive data
	 *
	 * @return the data
	 * @throws IOException networking data
	 */
	public Boolean receiveBoolean() throws IOException {
		return Boolean.valueOf(receive());
	}

	 /**
	 * send a list<br>
	 * Supported types: String, Integer, Boolean
	 *
	 * @param list the list
	 * @throws IOException              networking error
	 * @throws IllegalArgumentException unsupported listtype
	 */
	public void send(List<?> list) throws IOException, IllegalArgumentException {
	//public void send(List<?> list) throws IOException, IllegalArgumentException {
		send(list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj instanceof String) {
				send((String)obj);
			} else if (obj instanceof Integer) {
				send((Integer)obj);
			} else if (obj instanceof Boolean) {
				send((Boolean)obj);
			}else{
				throw new IllegalArgumentException("Cannot send unsupported Listtype. CanonicalName: " + obj.getClass().getCanonicalName() + " Name: " + obj.getClass().getName() + " TypeName: " + obj.getClass().getTypeName() + " toString: \"" + obj.toString() + "\"");
			}
		}
	}
	/**
	 * receive data
	 *
	 * @return the data
	 * @throws IOException networking data
	 */
	public List<String> receiveListString() throws IOException {
		List<String> res = new ArrayList<String>();
		int size = receiveInteger();


		for (int i = 0; i < size; i++) {
			res.add(receive());
		}
		return res;
	}

	/**
	 * receive data
	 *
	 * @return the data
	 * @throws IOException networking data
	 */
	public List<Integer> receiveListInteger() throws IOException {
		List<Integer> res = new ArrayList<Integer>();
		int size = receiveInteger();


		for (int i = 0; i < size; i++) {
			res.add(receiveInteger());
		}
		return res;
	}

	/**
	 * receive data
	 *
	 * @return the data
	 * @throws IOException networking data
	 */
	public List<Boolean> receiveListBoolean() throws IOException {
		List<Boolean> res = new ArrayList<Boolean>();
		int size = receiveInteger();


		for (int i = 0; i < size; i++) {
			res.add(receiveBoolean());
		}
		return res;
	}





	/**
	 * send an exception
	 *
	 * @param e the exception (null: no exception)
	 * @param origSource See {@link RemoteException#getOriginalSource()}.
	 * @throws IOException networking error
	 */
	public void send(Exception e, String origSource) throws IOException {
		if (e == null) {
			send(true);
		} else {
			send(false);
			send(origSource);
			send(e.getMessage());
			send(e.getClass().getCanonicalName());
		}
	}

	/**
	 * send an exception
	 *
	 * @param e the exception (null: no exception)
	 * @throws IOException networking error
	 */
	public void send(Exception e) throws IOException {
		send(e, null);
	}

	/**
	 * Receive and throw a remote exception. Does nothing if no exception received.
	 *
	 * @throws IOException     networking error
	 * @throws RemoteException the remote exception
	 */
	public void receiveRemoteException() throws IOException, RemoteException {
		boolean isnull = receiveBoolean();
		if (isnull) {
			return; // no exception thrown
		} else {
			String origSource = receive();
			String message = receive();
			String canonicalname = receive();
		throw new RemoteException(message, canonicalname, getRemoteSocketAddress(), origSource);
		
		}
	}



}
