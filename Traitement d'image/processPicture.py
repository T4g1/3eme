# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'processPicture.ui'
#
# Created: Fri Mar 16 21:52:29 2012
#      by: PyQt4 UI code generator 4.9.1
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
        MainWindow.resize(801, 595)
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
        self.label_2 = QtGui.QLabel(self.gridLayoutWidget)
        self.label_2.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.gridLayout.addWidget(self.label_2, 2, 0, 1, 1)
        self.label_3 = QtGui.QLabel(self.gridLayoutWidget)
        self.label_3.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.gridLayout.addWidget(self.label_3, 3, 0, 1, 1)
        self.imageSource = QtGui.QLabel(self.gridLayoutWidget)
        self.imageSource.setObjectName(_fromUtf8("imageSource"))
        self.gridLayout.addWidget(self.imageSource, 0, 0, 1, 1)
        self.imageResultat = QtGui.QLabel(self.gridLayoutWidget)
        self.imageResultat.setObjectName(_fromUtf8("imageResultat"))
        self.gridLayout.addWidget(self.imageResultat, 0, 1, 1, 1)
        self.comboBoxNomImage = QtGui.QComboBox(self.gridLayoutWidget)
        self.comboBoxNomImage.setObjectName(_fromUtf8("comboBoxNomImage"))
        self.gridLayout.addWidget(self.comboBoxNomImage, 3, 1, 1, 1)
        self.label = QtGui.QLabel(self.gridLayoutWidget)
        self.label.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.label.setObjectName(_fromUtf8("label"))
        self.gridLayout.addWidget(self.label, 1, 0, 1, 1)
        self.comboBoxNom = QtGui.QComboBox(self.gridLayoutWidget)
        self.comboBoxNom.setObjectName(_fromUtf8("comboBoxNom"))
        self.gridLayout.addWidget(self.comboBoxNom, 2, 1, 1, 1)
        self.comboBoxType = QtGui.QComboBox(self.gridLayoutWidget)
        self.comboBoxType.setSizeAdjustPolicy(QtGui.QComboBox.AdjustToContentsOnFirstShow)
        self.comboBoxType.setObjectName(_fromUtf8("comboBoxType"))
        self.comboBoxType.addItem(_fromUtf8(""))
        self.comboBoxType.addItem(_fromUtf8(""))
        self.gridLayout.addWidget(self.comboBoxType, 1, 1, 1, 1)
        self.applyButton = QtGui.QPushButton(self.gridLayoutWidget)
        self.applyButton.setObjectName(_fromUtf8("applyButton"))
        self.gridLayout.addWidget(self.applyButton, 7, 0, 1, 1)
        self.reapplyButton = QtGui.QPushButton(self.gridLayoutWidget)
        self.reapplyButton.setObjectName(_fromUtf8("reapplyButton"))
        self.gridLayout.addWidget(self.reapplyButton, 7, 1, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtGui.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 801, 22))
        self.menubar.setObjectName(_fromUtf8("menubar"))
        self.menuFile = QtGui.QMenu(self.menubar)
        self.menuFile.setObjectName(_fromUtf8("menuFile"))
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
        self.menuFile.addAction(self.actionSauver)
        self.menuFile.addAction(self.actionSauver_sous)
        self.menuFile.addSeparator()
        self.menuFile.addAction(self.actionQuitter)
        self.menubar.addAction(self.menuFile.menuAction())

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QtGui.QApplication.translate("MainWindow", "Traitement d\'image", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("MainWindow", "Nom de l\'operation :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("MainWindow", "Image ciblée :", None, QtGui.QApplication.UnicodeUTF8))
        self.imageSource.setText(QtGui.QApplication.translate("MainWindow", "imageSource", None, QtGui.QApplication.UnicodeUTF8))
        self.imageResultat.setText(QtGui.QApplication.translate("MainWindow", "imageResultat", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("MainWindow", "Type d\'operation :", None, QtGui.QApplication.UnicodeUTF8))
        self.comboBoxType.setItemText(0, QtGui.QApplication.translate("MainWindow", "Ajout de bruit", None, QtGui.QApplication.UnicodeUTF8))
        self.comboBoxType.setItemText(1, QtGui.QApplication.translate("MainWindow", "Filtrer l\'image", None, QtGui.QApplication.UnicodeUTF8))
        self.applyButton.setText(QtGui.QApplication.translate("MainWindow", "Appliquer depuis la source", None, QtGui.QApplication.UnicodeUTF8))
        self.reapplyButton.setText(QtGui.QApplication.translate("MainWindow", "Appliquer sur le resultat", None, QtGui.QApplication.UnicodeUTF8))
        self.menuFile.setTitle(QtGui.QApplication.translate("MainWindow", "File", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setText(QtGui.QApplication.translate("MainWindow", "Quitter", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+Q", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setText(QtGui.QApplication.translate("MainWindow", "Sauver", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+S", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver_sous.setText(QtGui.QApplication.translate("MainWindow", "Sauver sous ...", None, QtGui.QApplication.UnicodeUTF8))

