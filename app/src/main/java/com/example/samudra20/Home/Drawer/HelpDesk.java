package com.example.samudra20.Home.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samudra20.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
public class HelpDesk extends AppCompatActivity {




        private EditText questionInput;
        private WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.help_desk);

            questionInput = findViewById(R.id.questionInput);
            webView = findViewById(R.id.youtubeWebView);
            Button submitButton = findViewById(R.id.submitButton);

            // Configure WebView settings
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for YouTube playback
            webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String question = questionInput.getText().toString().trim();
                    String videoUrl = getYouTubeVideoUrl(question);

                    if (videoUrl != null) {
                        webView.loadUrl(videoUrl);  // Load the video URL in WebView
                    } else {
                        // You can handle cases where no relevant video is found
                        webView.loadData("<html><body><h2>No video found for the question</h2></body></html>", "text/html", "UTF-8");
                    }
                }
            });
        }

        // Method to return YouTube embed URL based on the user's question
        private String getYouTubeVideoUrl(String question) {
            if (question.equalsIgnoreCase("jellyfish sting")) {
                return "https://www.youtube.com/watch?v=UKkg7pCkjwg";  // Replace with your video ID
            } else if (question.equalsIgnoreCase("what are the safety measures for swimming in a rip cureent ")) {
                return "https://www.youtube.com/watch?v=obqXIm91_5U";  // Replace with your video ID
            } else if (question.equalsIgnoreCase("what should i do if i encounter a shark")) {
                return "https://www.youtube.com/watch?v=SLjrw9fzVcY";  // Replace with your video ID
            } else {
                return null;
            }
        }
    }