package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            BorderPane root = new BorderPane();
            //define the grid pane for the BMR form, will act as the structure for user interface
            GridPane grid = new GridPane();
            //set the padding between the form and the next one, i.e. Vgap
            grid.setVgap(10.0);
            //set the padding between the label and the form, i.e. Hgap
            grid.setHgap(5.0);
            //estimated the padding around the grid pane, not specified in the instructions
            grid.setPadding(new Insets(20));

            root.setCenter(grid);

            //create the scene for the user interface
            Scene scene = new Scene(root, 270, 310);

            ///////////
            // TITLE /
            /////////

            //create a new label to be the title
            Label title = new Label();
            //set the text of the label
            title.setText("BMR Calculator");
            //set the font of the label
            title.setFont(new Font(18.0));
            root.setTop(title);
            root.setAlignment(title,Pos.CENTER);

            ///////////////
            // AGE INPUT /
            /////////////

            //create a new label that will be the age label
            Label ageLabel = new Label();
            //set the text for the new age label
            ageLabel.setText("Age:");
            //add it to the grid
            grid.add(ageLabel, 0, 2);
            //create a textfield for the age input
            TextField ageTextfield = new TextField();
            //add the textfield to the grid
            grid.add(ageTextfield, 1, 2);

            //////////////////
            // GENDER INPUT /
            ////////////////

            //create a new label that will be the age label
            Label genderLabel = new Label();
            //set the text for the new age label
            genderLabel.setText("Gender:");
            //add it to the grid
            grid.add(genderLabel, 0, 3);

            //create radio buttons
            RadioButton maleOption = new RadioButton("Male");
            //set the male radio button to be selected by default
            maleOption.setSelected(true);
            RadioButton femaleOption = new RadioButton("Female");

            //create a toggle group
            ToggleGroup tg = new ToggleGroup();
            //add the radio buttons to the toggle group
            maleOption.setToggleGroup(tg);
            femaleOption.setToggleGroup(tg);

            //create the hbox that will be a node in the grid pane
            HBox hb = new HBox();
            //add the toggle group to the hbox
            hb.getChildren().addAll(maleOption, femaleOption);

            //add the hbox to the grid pane
            grid.add(hb, 1, 3);

            //////////////////
            // HEIGHT INPUT /
            ////////////////

            //create a new label that will be the height label
            Label heightLabel = new Label();
            //set the text for the new height label
            heightLabel.setText("Height:");
            //add it to the grid
            grid.add(heightLabel, 0, 4);
            //create a textfield for the height input
            TextField heightTextfield = new TextField();
            //add the textfield to the grid
            grid.add(heightTextfield, 1, 4);

            //////////////////
            // WEIGHT INPUT /
            ////////////////

            //create a new label that will be the weight label
            Label weightLabel = new Label();
            //set the text for the new weight label
            weightLabel.setText("Weight:");
            //add it to the grid
            grid.add(weightLabel, 0, 5);
            //create a textfield for the weight input
            TextField weightTextfield = new TextField();
            //add the textfield to the grid
            grid.add(weightTextfield, 1, 5);

            //////////////////////
            // CALCULATE BUTTON /
            ////////////////////

            //create a button that will be our calculate button
            Button calculateButton = new Button("Calculate");
            grid.add(calculateButton, 0, 6, 2, 1);

            //////////////////
            // OUTPUT LABEL /
            ////////////////

            //create a new label
            Label outputLabel = new Label();
            //set it to be at the bottom
            root.setBottom(outputLabel);
            //align it to be at the bottom center
            root.setAlignment(outputLabel,Pos.CENTER);
            //add padding to the bottom
            root.setPadding(new Insets(0,0,10,0));

            ////////////////////
            // EVENT HANDLERS /
            //////////////////

            //event handler for the calculate button
            calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //retrieve the values from the textfields
                    Double age = Double.parseDouble(ageTextfield.getText());
                    Double height = Double.parseDouble(heightTextfield.getText());
                    Double weight = Double.parseDouble(weightTextfield.getText());

                    //create double that will store our bmrValue
                    double bmrValue;

                    //based on the gender set the brmValue
                    //if the male option is selected
                    if(maleOption.isSelected())
                        bmrValue = (10*weight)+(6.25*height)-(5*age)+5;
                    //otherwise the female option is selected
                    else
                        bmrValue = (10*weight)+(6.25*height)-(5-age)-161;

                    //output the result
                    outputLabel.setText("BMR = "+bmrValue+" Calories");
                }
            });
            //set the scen for the stage
            primaryStage.setScene(scene);
            //shoe the stage
            primaryStage.show();
            //catch any exception and print the stack trace
        } catch (Exception e) {
            //print the stack trace
            e.printStackTrace();
        }
    }

    /**
     * Main method of the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //launch the application
        launch(args);
    }
}
