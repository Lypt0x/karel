# Installation

1. Download [karel](https://github.com/Lypt0x/karel/archive/refs/heads/release-darktheme-binary.zip)
2. Extract the files
3. Run karel.exe


# Annotation
The jar has been converted by [Launch4j](http://launch4j.sourceforge.net/)
If you want to install it on your own, then follow the following steps:

### Requirements
- Installed [Java 1.8](https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u282-b08/OpenJDK8U-jdk_x64_windows_hotspot_8u282b08.msi)


### Steps
1. Download and Install [Launch4j](https://sourceforge.net/projects/launch4j/files/launch4j-3/3.14/)

3. Start Launch4j
4. Select the **Output file** under **Basic**. There you can type something like `/path/to/exe/myexe.exe`
5. Select the **Jar** under **Basic**. This jar-file will get converted into the **Output file**
6. Go to your **JRE-Directory** and copy `/bin` and `/lib` to the Location where your jar-file is at. My suggestion is, that you're gonna create a Directory called `wrapper`. There you can paste the copied Directories.
7. Type in **Bundled JRE paths** under **JRE** the Path to your pasted **JRE-Directory**. For me it was `wrapper`.
8. Type in **Min JRE version** under **JRE** nearby **Search options** the min-JRE-version. For me it was `1.8.0`.
9. Press **Build Wrapper**-wheel in the top.
10. You're Done! Now you can close Launch4j.
