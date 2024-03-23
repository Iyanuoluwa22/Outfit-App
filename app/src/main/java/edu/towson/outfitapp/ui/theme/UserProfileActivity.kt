package edu.towson.outfitapp.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.outfitapp.R
import edu.towson.outfitapp.adapter.StitchesAdapter
import edu.towson.outfitapp.databinding.ActivityUserProfileBinding
import edu.towson.outfitapp.model.Stitch

class UserProfileActivity : AppCompatActivity() {

    private lateinit var stitchesAdapter: StitchesAdapter
    private var binding = ActivityUserProfileBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Sample data (replace with your actual data)
        val stitches = listOf(
            Stitch("https://example.com/stitch1.jpg", 10, 5),
            Stitch("https://example.com/stitch2.jpg", 20, 8),
            Stitch("https://example.com/stitch3.jpg", 15, 3)
        )

        // Initialize RecyclerView
        stitchesAdapter = StitchesAdapter(stitches)

        binding.stitchesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserProfileActivity)
            adapter = stitchesAdapter
        }

        // Set user data (replace with actual user data)
        binding.profileImageView.setImageResource(0) // Set it to -> R.drawable.default_profile_image
        binding.usernameTextView.text = "Username"
        binding.followersCountTextView.text = "Followers: 100"
        binding.followingCountTextView.text = "Following: 50"

        // Set click listener for follow button
        binding.followButton.setOnClickListener {
            // Implement follow/unfollow logic here
        }

        // Set click listener for upload photo button
        binding.uploadPhotoButton.setOnClickListener {
            // Implement photo upload functionality here
        }
    }
}
