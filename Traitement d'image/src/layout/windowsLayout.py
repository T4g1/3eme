# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'windowsLayout.ui'
#
# Created: Mon Oct 22 19:34:18 2012
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
        MainWindow.resize(793, 594)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(MainWindow.sizePolicy().hasHeightForWidth())
        MainWindow.setSizePolicy(sizePolicy)
        self.centralwidget = QtGui.QWidget(MainWindow)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.centralwidget.sizePolicy().hasHeightForWidth())
        self.centralwidget.setSizePolicy(sizePolicy)
        self.centralwidget.setObjectName(_fromUtf8("centralwidget"))
        self.verticalLayout_4 = QtGui.QVBoxLayout(self.centralwidget)
        self.verticalLayout_4.setSpacing(0)
        self.verticalLayout_4.setMargin(0)
        self.verticalLayout_4.setObjectName(_fromUtf8("verticalLayout_4"))
        self.gridLayout = QtGui.QGridLayout()
        self.gridLayout.setMargin(5)
        self.gridLayout.setSpacing(6)
        self.gridLayout.setObjectName(_fromUtf8("gridLayout"))
        self.lab_imageresultat = QtGui.QLabel(self.centralwidget)
        self.lab_imageresultat.setObjectName(_fromUtf8("lab_imageresultat"))
        self.gridLayout.addWidget(self.lab_imageresultat, 0, 1, 1, 1)
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setSizeConstraint(QtGui.QLayout.SetNoConstraint)
        self.horizontalLayout.setObjectName(_fromUtf8("horizontalLayout"))
        self.modeNormal = QtGui.QPushButton(self.centralwidget)
        self.modeNormal.setToolTip(_fromUtf8(""))
        self.modeNormal.setObjectName(_fromUtf8("modeNormal"))
        self.horizontalLayout.addWidget(self.modeNormal)
        self.modeROI = QtGui.QPushButton(self.centralwidget)
        self.modeROI.setObjectName(_fromUtf8("modeROI"))
        self.horizontalLayout.addWidget(self.modeROI)
        self.gridLayout.addLayout(self.horizontalLayout, 2, 0, 1, 1)
        self.baseView = QtGui.QGraphicsView(self.centralwidget)
        self.baseView.setObjectName(_fromUtf8("baseView"))
        self.gridLayout.addWidget(self.baseView, 1, 0, 1, 1)
        self.toBasePicture = QtGui.QPushButton(self.centralwidget)
        self.toBasePicture.setObjectName(_fromUtf8("toBasePicture"))
        self.gridLayout.addWidget(self.toBasePicture, 2, 1, 1, 1)
        self.resultView = QtGui.QGraphicsView(self.centralwidget)
        self.resultView.setObjectName(_fromUtf8("resultView"))
        self.gridLayout.addWidget(self.resultView, 1, 1, 1, 1)
        self.lab_imagesource = QtGui.QLabel(self.centralwidget)
        self.lab_imagesource.setMaximumSize(QtCore.QSize(16777215, 10))
        self.lab_imagesource.setObjectName(_fromUtf8("lab_imagesource"))
        self.gridLayout.addWidget(self.lab_imagesource, 0, 0, 1, 1)
        self.verticalLayout_4.addLayout(self.gridLayout)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtGui.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 793, 21))
        self.menubar.setObjectName(_fromUtf8("menubar"))
        self.menuFile = QtGui.QMenu(self.menubar)
        self.menuFile.setObjectName(_fromUtf8("menuFile"))
        self.menuImage = QtGui.QMenu(self.menubar)
        self.menuImage.setObjectName(_fromUtf8("menuImage"))
        self.menuThreshold = QtGui.QMenu(self.menubar)
        self.menuThreshold.setObjectName(_fromUtf8("menuThreshold"))
        self.menuHistogramme = QtGui.QMenu(self.menubar)
        self.menuHistogramme.setObjectName(_fromUtf8("menuHistogramme"))
        self.menuFiltres = QtGui.QMenu(self.menubar)
        self.menuFiltres.setObjectName(_fromUtf8("menuFiltres"))
        self.menuAutres = QtGui.QMenu(self.menubar)
        self.menuAutres.setObjectName(_fromUtf8("menuAutres"))
        self.menuAffinage_des_contours = QtGui.QMenu(self.menubar)
        self.menuAffinage_des_contours.setObjectName(_fromUtf8("menuAffinage_des_contours"))
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
        self.actionModifierTaille = QtGui.QAction(MainWindow)
        self.actionModifierTaille.setObjectName(_fromUtf8("actionModifierTaille"))
        self.actionModifierPalette = QtGui.QAction(MainWindow)
        self.actionModifierPalette.setObjectName(_fromUtf8("actionModifierPalette"))
        self.actionExpansion = QtGui.QAction(MainWindow)
        self.actionExpansion.setObjectName(_fromUtf8("actionExpansion"))
        self.actionExtraction = QtGui.QAction(MainWindow)
        self.actionExtraction.setObjectName(_fromUtf8("actionExtraction"))
        self.actionSimple = QtGui.QAction(MainWindow)
        self.actionSimple.setObjectName(_fromUtf8("actionSimple"))
        self.actionMultiple = QtGui.QAction(MainWindow)
        self.actionMultiple.setObjectName(_fromUtf8("actionMultiple"))
        self.actionAfficherHist = QtGui.QAction(MainWindow)
        self.actionAfficherHist.setObjectName(_fromUtf8("actionAfficherHist"))
        self.actionEgaliserHist = QtGui.QAction(MainWindow)
        self.actionEgaliserHist.setObjectName(_fromUtf8("actionEgaliserHist"))
        self.actionMedian = QtGui.QAction(MainWindow)
        self.actionMedian.setObjectName(_fromUtf8("actionMedian"))
        self.actionMoyen = QtGui.QAction(MainWindow)
        self.actionMoyen.setObjectName(_fromUtf8("actionMoyen"))
        self.actionGaussien = QtGui.QAction(MainWindow)
        self.actionGaussien.setObjectName(_fromUtf8("actionGaussien"))
        self.actionLaplacien = QtGui.QAction(MainWindow)
        self.actionLaplacien.setObjectName(_fromUtf8("actionLaplacien"))
        self.actionKirsh = QtGui.QAction(MainWindow)
        self.actionKirsh.setObjectName(_fromUtf8("actionKirsh"))
        self.actionSobel = QtGui.QAction(MainWindow)
        self.actionSobel.setObjectName(_fromUtf8("actionSobel"))
        self.actionPrewitt = QtGui.QAction(MainWindow)
        self.actionPrewitt.setObjectName(_fromUtf8("actionPrewitt"))
        self.actionRoberts = QtGui.QAction(MainWindow)
        self.actionRoberts.setObjectName(_fromUtf8("actionRoberts"))
        self.actionApplicationAvancee = QtGui.QAction(MainWindow)
        self.actionApplicationAvancee.setObjectName(_fromUtf8("actionApplicationAvancee"))
        self.actionErosion = QtGui.QAction(MainWindow)
        self.actionErosion.setObjectName(_fromUtf8("actionErosion"))
        self.actionDilatation = QtGui.QAction(MainWindow)
        self.actionDilatation.setObjectName(_fromUtf8("actionDilatation"))
        self.actionOuverture = QtGui.QAction(MainWindow)
        self.actionOuverture.setObjectName(_fromUtf8("actionOuverture"))
        self.actionFermeture = QtGui.QAction(MainWindow)
        self.actionFermeture.setObjectName(_fromUtf8("actionFermeture"))
        self.actionApplicationAvanceeAutre = QtGui.QAction(MainWindow)
        self.actionApplicationAvanceeAutre.setObjectName(_fromUtf8("actionApplicationAvanceeAutre"))
        self.actionAffiner = QtGui.QAction(MainWindow)
        self.actionAffiner.setObjectName(_fromUtf8("actionAffiner"))
        self.menuFile.addAction(self.actionOuvrir)
        self.menuFile.addSeparator()
        self.menuFile.addAction(self.actionQuitter)
        self.menuImage.addAction(self.actionModifierTaille)
        self.menuImage.addAction(self.actionModifierPalette)
        self.menuImage.addSeparator()
        self.menuImage.addAction(self.actionExpansion)
        self.menuThreshold.addAction(self.actionSimple)
        self.menuThreshold.addAction(self.actionMultiple)
        self.menuHistogramme.addAction(self.actionAfficherHist)
        self.menuHistogramme.addAction(self.actionEgaliserHist)
        self.menuFiltres.addAction(self.actionMedian)
        self.menuFiltres.addAction(self.actionMoyen)
        self.menuFiltres.addAction(self.actionGaussien)
        self.menuFiltres.addSeparator()
        self.menuFiltres.addAction(self.actionLaplacien)
        self.menuFiltres.addAction(self.actionKirsh)
        self.menuFiltres.addAction(self.actionSobel)
        self.menuFiltres.addAction(self.actionPrewitt)
        self.menuFiltres.addAction(self.actionRoberts)
        self.menuFiltres.addSeparator()
        self.menuFiltres.addAction(self.actionApplicationAvancee)
        self.menuAutres.addAction(self.actionErosion)
        self.menuAutres.addAction(self.actionDilatation)
        self.menuAutres.addSeparator()
        self.menuAutres.addAction(self.actionOuverture)
        self.menuAutres.addAction(self.actionFermeture)
        self.menuAutres.addSeparator()
        self.menuAutres.addAction(self.actionApplicationAvanceeAutre)
        self.menuAffinage_des_contours.addAction(self.actionAffiner)
        self.menubar.addAction(self.menuFile.menuAction())
        self.menubar.addAction(self.menuImage.menuAction())
        self.menubar.addAction(self.menuThreshold.menuAction())
        self.menubar.addAction(self.menuHistogramme.menuAction())
        self.menubar.addAction(self.menuFiltres.menuAction())
        self.menubar.addAction(self.menuAutres.menuAction())
        self.menubar.addAction(self.menuAffinage_des_contours.menuAction())

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QtGui.QApplication.translate("MainWindow", "Traitement d\'image", None, QtGui.QApplication.UnicodeUTF8))
        self.lab_imageresultat.setText(QtGui.QApplication.translate("MainWindow", "Image résultat:", None, QtGui.QApplication.UnicodeUTF8))
        self.modeNormal.setText(QtGui.QApplication.translate("MainWindow", "Aucun", None, QtGui.QApplication.UnicodeUTF8))
        self.modeROI.setText(QtGui.QApplication.translate("MainWindow", "R.O.I.", None, QtGui.QApplication.UnicodeUTF8))
        self.toBasePicture.setText(QtGui.QApplication.translate("MainWindow", "<- Prendre comme image de base", None, QtGui.QApplication.UnicodeUTF8))
        self.lab_imagesource.setText(QtGui.QApplication.translate("MainWindow", "Image source:", None, QtGui.QApplication.UnicodeUTF8))
        self.menuFile.setTitle(QtGui.QApplication.translate("MainWindow", "Fichier", None, QtGui.QApplication.UnicodeUTF8))
        self.menuImage.setTitle(QtGui.QApplication.translate("MainWindow", "Image", None, QtGui.QApplication.UnicodeUTF8))
        self.menuThreshold.setTitle(QtGui.QApplication.translate("MainWindow", "Seuillage", None, QtGui.QApplication.UnicodeUTF8))
        self.menuHistogramme.setTitle(QtGui.QApplication.translate("MainWindow", "Histogramme", None, QtGui.QApplication.UnicodeUTF8))
        self.menuFiltres.setTitle(QtGui.QApplication.translate("MainWindow", "Filtres", None, QtGui.QApplication.UnicodeUTF8))
        self.menuAutres.setTitle(QtGui.QApplication.translate("MainWindow", "Autres", None, QtGui.QApplication.UnicodeUTF8))
        self.menuAffinage_des_contours.setTitle(QtGui.QApplication.translate("MainWindow", "Affinage des contours", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setText(QtGui.QApplication.translate("MainWindow", "Quitter", None, QtGui.QApplication.UnicodeUTF8))
        self.actionQuitter.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+Q", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setText(QtGui.QApplication.translate("MainWindow", "Sauver", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver.setShortcut(QtGui.QApplication.translate("MainWindow", "Ctrl+S", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSauver_sous.setText(QtGui.QApplication.translate("MainWindow", "Sauver sous ...", None, QtGui.QApplication.UnicodeUTF8))
        self.actionOuvrir.setText(QtGui.QApplication.translate("MainWindow", "Ouvrir une image", None, QtGui.QApplication.UnicodeUTF8))
        self.actionModifierTaille.setText(QtGui.QApplication.translate("MainWindow", "Modifier la taille", None, QtGui.QApplication.UnicodeUTF8))
        self.actionModifierPalette.setText(QtGui.QApplication.translate("MainWindow", "Modifier la palette", None, QtGui.QApplication.UnicodeUTF8))
        self.actionExpansion.setText(QtGui.QApplication.translate("MainWindow", "Expansion/Extraction", None, QtGui.QApplication.UnicodeUTF8))
        self.actionExtraction.setText(QtGui.QApplication.translate("MainWindow", "Extraction", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSimple.setText(QtGui.QApplication.translate("MainWindow", "Simple", None, QtGui.QApplication.UnicodeUTF8))
        self.actionMultiple.setText(QtGui.QApplication.translate("MainWindow", "Multiple", None, QtGui.QApplication.UnicodeUTF8))
        self.actionAfficherHist.setText(QtGui.QApplication.translate("MainWindow", "Afficher", None, QtGui.QApplication.UnicodeUTF8))
        self.actionEgaliserHist.setText(QtGui.QApplication.translate("MainWindow", "Egaliser", None, QtGui.QApplication.UnicodeUTF8))
        self.actionMedian.setText(QtGui.QApplication.translate("MainWindow", "Médian", None, QtGui.QApplication.UnicodeUTF8))
        self.actionMoyen.setText(QtGui.QApplication.translate("MainWindow", "Moyen", None, QtGui.QApplication.UnicodeUTF8))
        self.actionGaussien.setText(QtGui.QApplication.translate("MainWindow", "Gaussien", None, QtGui.QApplication.UnicodeUTF8))
        self.actionLaplacien.setText(QtGui.QApplication.translate("MainWindow", "Laplacien", None, QtGui.QApplication.UnicodeUTF8))
        self.actionKirsh.setText(QtGui.QApplication.translate("MainWindow", "Kirsh", None, QtGui.QApplication.UnicodeUTF8))
        self.actionSobel.setText(QtGui.QApplication.translate("MainWindow", "Sobel", None, QtGui.QApplication.UnicodeUTF8))
        self.actionPrewitt.setText(QtGui.QApplication.translate("MainWindow", "Prewitt", None, QtGui.QApplication.UnicodeUTF8))
        self.actionRoberts.setText(QtGui.QApplication.translate("MainWindow", "Roberts", None, QtGui.QApplication.UnicodeUTF8))
        self.actionApplicationAvancee.setText(QtGui.QApplication.translate("MainWindow", "Application avancée ...", None, QtGui.QApplication.UnicodeUTF8))
        self.actionErosion.setText(QtGui.QApplication.translate("MainWindow", "Erosion", None, QtGui.QApplication.UnicodeUTF8))
        self.actionDilatation.setText(QtGui.QApplication.translate("MainWindow", "Dilatation", None, QtGui.QApplication.UnicodeUTF8))
        self.actionOuverture.setText(QtGui.QApplication.translate("MainWindow", "Ouverture", None, QtGui.QApplication.UnicodeUTF8))
        self.actionFermeture.setText(QtGui.QApplication.translate("MainWindow", "Fermeture", None, QtGui.QApplication.UnicodeUTF8))
        self.actionApplicationAvanceeAutre.setText(QtGui.QApplication.translate("MainWindow", "Application avancée ...", None, QtGui.QApplication.UnicodeUTF8))
        self.actionAffiner.setText(QtGui.QApplication.translate("MainWindow", "Affiner", None, QtGui.QApplication.UnicodeUTF8))

