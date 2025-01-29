import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GUISetUP 
{
		private TextField From = new TextField();
		private Label from = new Label("From :");

		private TextField _Socket = new TextField();
		private Label socket = new Label("Socket Number :");


		private TextField HostName = new TextField();
		private Label hostName = new Label("Host Name :");

		private TextField To = new TextField();
		private Label to = new Label("To :");

		private TextField Cc = new TextField();
		private Label cc = new Label("Cc :");


		private TextField Subject = new TextField();
		private Label subject = new Label("Subject :");


		private Button Send = new Button("Send");

		private TextArea Text = new TextArea();

		private String SocketNumber = _Socket.getText();
		private VBox Vertical = new VBox();

		private BorderPane layout = new BorderPane();

		private Scene sc = new Scene(layout,600,600);



		public GUISetUP() 
		{
			
			
			this.Text.setPrefHeight(400);
			this.Text.setPrefWidth(400);

			this.Vertical.getChildren().addAll(this.socket,this._Socket,this.hostName,
					this.HostName,this.from,this.From,this.to,
					this.To,this.cc,this.Cc,this.subject,this.Subject);

			this.layout.setBottom(Send); 
			this.layout.setCenter(Text); 
			this.layout.setTop(Vertical);
			
			Send.setOnAction(arg0 -> new EmailClient(this));
		}


		/**
		 * @return the _Socket
		 */
		public TextField get_Socket() {
			return _Socket;
		}

		/**
		 * @return the hostName
		 */
		public TextField getHostName() {
			return HostName;
		}


		/**
		 * @return the from
		 */
		public TextField getFrom() {
			return From;
		}


		/**
		 * @param from the from to set
		 */
		public void setFrom(TextField from) {
			From = from;
		}


		/**
		 * @return the to
		 */
		public TextField getTo() {
			return To;
		}


		/**
		 * @param to the to to set
		 */
		public void setTo(TextField to) {
			To = to;
		}


		/**
		 * @return the cc
		 */
		public TextField getCc() {
			return Cc;
		}


		/**
		 * @param cc the cc to set
		 */
		public void setCc(TextField cc) {
			Cc = cc;
		}


		/**
		 * @return the subject
		 */
		public TextField getSubject() {
			return Subject;
		}


		/**
		 * @param subject the subject to set
		 */
		public void setSubject(TextField subject) {
			Subject = subject;
		}


		/**
		 * @return the text
		 */
		public TextArea getText() {
			return Text;
		}


		/**
		 * @param text the text to set
		 */
		public void setText(TextArea text) {
			Text = text;
		}


		/**
		 * @return the socketNumber
		 */
		public String getSocketNumber() {
			return SocketNumber;
		}


		/**
		 * @param socketNumber the socketNumber to set
		 */
		public void setSocketNumber(String socketNumber) {
			SocketNumber = socketNumber;
		}


		/**
		 * @return the layout
		 */
		public BorderPane getLayout() {
			return layout;
		}


		/**
		 * @param hostName the hostName to set
		 */
		public void setHostName(TextField hostName) {
			HostName = hostName;
		}
		
		
		public Scene getScene()
		{
			return sc;
		}
	

}
