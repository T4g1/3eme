# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'windowsLayout.ui'
#
# Created: Thu Sep 20 08:43:54 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName(_fromUtf8("MainWindow"))
        MainWindow.resize(798, 599)
        self.centralwidget = QtGui.QWidget(MainWindow)
        self.centralwidget.setObjectName(_fromUtf8("centralwidget"))
        self.gridLayoutWidget = QtGui.QWidget(self.centralwidget)
        self.gridLayoutWidget.setGeometry(QtCore.QRect(0, 0, 791, 551))
        self.gridLayoutWidget.setObjectName(_fromUtf8("gridLayoutWidget"))
        self.gridLayout = QtGui.QGridLayout(self.gridLayoutWidget)
        self.gridLayout.setMargin(5)
        self.gridLayout.setSpacing(6)
        self.gridLayout.setMargin(0)
        self.gridLayout.setObjectName(_fromUtf8("gridLayout"))
        self.resultPicture = QtGui.QLabel(self.gridLayoutWidget)
        self.resultPicture.setText(_fromUtf8(""))
        self.resultPicture.setObjectName(_fromUtf8("resultPicture"))
        self.gridLayout.addWidget(self.resultPicture, 1, 1, 1, 1)
        self.basePicture = QtGui.QLabel(self.gridLayoutWidget)
        self.basePicture.setCursor(QtGui.QCursor(QtCore.Qt.ArrowCursor))
        self.basePicture.setMouseTracking(False)
        self.basePicture.setText(_fromUtf8(""))
        self.basePicture.setObjectName(_fromUtf8("basePicture"))
        self.gridLayout.addWidget(self.basePicture, 1, 0, 1, 1)
        self.lab_imagesource = QtGui.QLabel(self.gridLayoutWidget)
        self.lab_imagesource.setMaximumSize(QtCore.QSize(16777215, 10))
        self.lab_imagesource.setObjectName(_fromUtf8("lab_imagesource"))
        self.gridLayout.addWidget(self.lab_imagesource, 0, 0, 1, 1)
        self.lab_imageresultat = QtGui.QLabel(self.gridLayoutWidget)
        self.lab_imageresultat.setObjectName(_fromUtf8("lab_imageresultat"))
        self.gridLayout.addWidget(self.lab_imageresultat, 0, 1, 1, 1)
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setSizeConstraint(QtGui.QLayout.SetNoConstraint)
        self.horizontalLayout.setObjectName(_fromUtf8("horizontalLayout"))
        self.modeNormal = QtGui.QPushButton(self.gridLayoutWidget)
        self.modeNormal.setToolTip(_fromUtf8(""))
        self.modeNormal.setObjectName(_fromUtf8("modeNormal"))
        self.horizontalLayout.addWidget(self.modeNormal)
        self.modeROI = QtGui.QPushButton(self.gridLayoutWidget)
        self.modeROI.setObjectName(_fromUtf8("modeROI"))
        self.horizontalLayout.addWidget(self.modeROI)
        self.gridLayout.addLayout(self.horizontalLayout, 2, 0, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtGui.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 798, 21))
        self.menubar.setObjectName(_fromUtf8("menubar"))
        self.menuFile = QtGui.QMenu(self.menubar)
        self.menuFile.setObjectName(_fromUtf8("menuFile"))
        self.menuImage = QtGui.QMenu(self.menubar)
        self.menuImage.setObjectName(_fromUtf8("menuImage"))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtGui.QStatusBar(MainWindow)
        self.statusbar.setObjectName(_fromUtf8("statusbar"))
        MainWindow.setStatusBar(self.statusbar)
        self.actionQuitter = QtGui.QAction(MainWindow)
        self.actionQuitter.setObjectName(_fromUtf8("actionQuitter"))
        self.actionSauver = QtGui.QAction(MainWindow)
        self.actionSauver.setObjectName(_fromUtf8("actionSauver"))
        self.actionSauver_sous = QtGui.QAction(MainWindow)
        self.actionSauver_sous.setObjectName(_fromUtf8("actionSauver_sous"))
        self.actionOuvrir = QtGui.QAction(MainWindow)
        self.actionOuvrir.setObjectName(_fromUtf8("actionOuvrir"))
        self.actionTaille = QtGui.QAction(MainWindow)
        self.actionTaille.setObjectName(_fromUtf8("actionTaille"))
        self.actionPalette = QtGui.QAction(MainWindow)
        self.actionPalette.setObjectName(_fromUtf8("actionPalette"))
        self.menuFile.addAction(self.actionOuvrir)
        self.menuFile.addSeparator()
        self.menuFile.addAction(self.actionQuitter)
        self.menuImage.addAction(self.actionTaille)
        self.menuImage.addAction(self.actionPalette)
        self.menubar.addAction(self.menuFile.menuAction())
        self.menubar.addAction(self.menuImage.menuAction())

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QtGui.QApplication.translate("MainWindow", "Traitement d\'image", None, QtGui.QApplication.UnicodeUTF8))
        self.lab_imagesource.setText(QtGui.QApplication.translate("MainWindow", "Image source:", None, QtGui.QApplication.UnicodeUTF8))
        self.lab_imageresultat.setText(QtGui.QApplication.translate("MainWindow", "Image résultat:", None, QtGui.QApplication.UnicodeUTF8))
        self.modeNormal.setText(QtGui.QApplication.translate("MainWindow", "Aucun", None, QtGui.QApplication.UnicodeUTF8))
        self.modeROI.setText(QtGui.QApplication.translate("MainWindow", "R.O.I.", None, QtGui.QApplication.UnicodeUTF8))
        self.menuFile.setTitle(QtGui.QApplication.translate("MainWindow", "Fichier", None, QtGui.QApplication.UnicodeUTF8))
        self.menuImage.setTitle(QtGui.QApplication.translate("MainWindow", "Image", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setText(QtGui.QApplication.translate("MainWindow", "Quitter", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+Q", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setText(QtGui.QApplication.translate("MainWindow", "Sauver", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+S", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver_sous.setText(QtGui.QApplication.translate("MainWindow", "Sauver sous ...", None, QtGui.QApplication.UnicodeUTF8))
        self.actionOuvrir.setText(QtGui.QApplication.translate("MainWindow", "Ouvrir une image", None, QtGui.QApplication.UnicodeUTF8))
        self.actionTaille.setText(QtGui.QApplication.translate("MainWindow", "Taille", None, QtGui.QApplication.UnicodeUTF8))
        self.actionPalette.setText(QtGui.QApplication.translate("MainWindow", "Palette", None, QtGui.QApplication.UnicodeUTF8))

