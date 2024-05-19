# Tea Leaf Planner 🍵

## About 🫖
This is a motion planning library designed specifically for the First Tech Challenge (FTC). It provides tools and utilities to assist FTC teams in creating efficient and precise motion paths for their robots.

## Installation ✍
To install the library, in `build.dependencies.gradle` add:
</br>

   ```gradle
   repositories { 
        jcenter()
        mavenCentral()
        maven { url "https://jitpack.io" }
   }
   ```  

Afterward in your team code module's `build.gradle` add:
</br>

   ```gradle
    dependencies {
        implementation 'com.github.Tea505:TeaLeafPlanner:Tag'
    }
   ```

Replacing `TAG` with the latest release or tag. Below is the latest Tag released.
</br>
</br>
[![](https://jitpack.io/v/Tea505/TeaLeafPlanner.svg)](https://jitpack.io/#Tea505/TeaLeafPlanner)

### Features 👀
* **Path Generation:** Easily generate smooth and optimized motion paths for your robot to follow using positional or pure pursuit actions.
* **Action Following:** Implement controllers to accurately follow generated paths, taking into account factors like velocity, acceleration, and wheel odometry.
* **Integration with FTC SDK:** Seamlessly integrate the library with the FTC Software Development Kit (SDK) for easy integration into your FTC projects.
* **Customization:** Fine-tune motion profiles and controllers to suit the specific requirements of your robot and game challenges.

### Getting Started
To start using the FTC Motion Planning Library in your FTC projects, follow these steps:

Clone or download the library repository to your development environment by following installation steps above. 
Explore the documentation and examples to learn how to generate paths and implement trajectory following in your robot code.
Experiment with different configurations and settings to optimize performance for your robot.

### Documentation
For detailed documentation on how to use the library, including API references and usage examples, please refer to the Wiki.

### Contributing
Contributions to the FTC Motion Planning Library are welcome! If you have ideas for improvements, new features, or bug fixes, please open an issue or submit a pull request on GitHub.

### License
This project is licensed under the MIT License - see the LICENSE file for details.

