namespace ClientServiceWeb
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.TabPage loosePartieTab;
            this.refreshLoose = new System.Windows.Forms.Button();
            this.displayLooser = new System.Windows.Forms.DataGridView();
            this.tabControl = new System.Windows.Forms.TabControl();
            this.playerTab = new System.Windows.Forms.TabPage();
            this.updatePlayer = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.deletePlayer = new System.Windows.Forms.Button();
            this.pseudoField = new System.Windows.Forms.TextBox();
            this.addPlayer = new System.Windows.Forms.Button();
            this.refreshPlayer = new System.Windows.Forms.Button();
            this.displayPlayers = new System.Windows.Forms.DataGridView();
            this.scoreTab = new System.Windows.Forms.TabPage();
            this.refreshScore = new System.Windows.Forms.Button();
            this.displayHighScore = new System.Windows.Forms.DataGridView();
            this.winPartieTab = new System.Windows.Forms.TabPage();
            this.partieCountField = new System.Windows.Forms.Label();
            this.refreshpartie = new System.Windows.Forms.Button();
            this.displayPartieParJoueur = new System.Windows.Forms.DataGridView();
            loosePartieTab = new System.Windows.Forms.TabPage();
            loosePartieTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayLooser)).BeginInit();
            this.tabControl.SuspendLayout();
            this.playerTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayPlayers)).BeginInit();
            this.scoreTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayHighScore)).BeginInit();
            this.winPartieTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayPartieParJoueur)).BeginInit();
            this.SuspendLayout();
            // 
            // loosePartieTab
            // 
            loosePartieTab.AccessibleName = "loosePartieTab";
            loosePartieTab.Controls.Add(this.refreshLoose);
            loosePartieTab.Controls.Add(this.displayLooser);
            loosePartieTab.Location = new System.Drawing.Point(4, 22);
            loosePartieTab.Name = "loosePartieTab";
            loosePartieTab.Padding = new System.Windows.Forms.Padding(3);
            loosePartieTab.Size = new System.Drawing.Size(737, 349);
            loosePartieTab.TabIndex = 3;
            loosePartieTab.Text = "Parties perdues";
            loosePartieTab.UseVisualStyleBackColor = true;
            // 
            // refreshLoose
            // 
            this.refreshLoose.Location = new System.Drawing.Point(0, 262);
            this.refreshLoose.Name = "refreshLoose";
            this.refreshLoose.Size = new System.Drawing.Size(75, 23);
            this.refreshLoose.TabIndex = 1;
            this.refreshLoose.Text = "Actualiser";
            this.refreshLoose.UseVisualStyleBackColor = true;
            this.refreshLoose.Click += new System.EventHandler(this.refreshLoose_Click);
            // 
            // displayLooser
            // 
            this.displayLooser.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.displayLooser.Location = new System.Drawing.Point(0, 0);
            this.displayLooser.Name = "displayLooser";
            this.displayLooser.Size = new System.Drawing.Size(398, 256);
            this.displayLooser.TabIndex = 0;
            // 
            // tabControl
            // 
            this.tabControl.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tabControl.Controls.Add(this.playerTab);
            this.tabControl.Controls.Add(this.scoreTab);
            this.tabControl.Controls.Add(this.winPartieTab);
            this.tabControl.Controls.Add(loosePartieTab);
            this.tabControl.Location = new System.Drawing.Point(-1, 0);
            this.tabControl.Name = "tabControl";
            this.tabControl.SelectedIndex = 0;
            this.tabControl.Size = new System.Drawing.Size(745, 375);
            this.tabControl.TabIndex = 0;
            this.tabControl.Selected += new System.Windows.Forms.TabControlEventHandler(this.tabControlSelected);
            // 
            // playerTab
            // 
            this.playerTab.AccessibleName = "playerTab";
            this.playerTab.Controls.Add(this.updatePlayer);
            this.playerTab.Controls.Add(this.label2);
            this.playerTab.Controls.Add(this.deletePlayer);
            this.playerTab.Controls.Add(this.pseudoField);
            this.playerTab.Controls.Add(this.addPlayer);
            this.playerTab.Controls.Add(this.refreshPlayer);
            this.playerTab.Controls.Add(this.displayPlayers);
            this.playerTab.Location = new System.Drawing.Point(4, 22);
            this.playerTab.Name = "playerTab";
            this.playerTab.Padding = new System.Windows.Forms.Padding(3);
            this.playerTab.Size = new System.Drawing.Size(737, 349);
            this.playerTab.TabIndex = 0;
            this.playerTab.Text = "Joueurs";
            this.playerTab.UseVisualStyleBackColor = true;
            // 
            // updatePlayer
            // 
            this.updatePlayer.Location = new System.Drawing.Point(307, 320);
            this.updatePlayer.Name = "updatePlayer";
            this.updatePlayer.Size = new System.Drawing.Size(75, 23);
            this.updatePlayer.TabIndex = 5;
            this.updatePlayer.Text = "Modifier";
            this.updatePlayer.UseVisualStyleBackColor = true;
            this.updatePlayer.Click += new System.EventHandler(this.updatePlayer_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(90, 300);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(46, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Pseudo:";
            // 
            // deletePlayer
            // 
            this.deletePlayer.Location = new System.Drawing.Point(349, 295);
            this.deletePlayer.Name = "deletePlayer";
            this.deletePlayer.Size = new System.Drawing.Size(75, 23);
            this.deletePlayer.TabIndex = 4;
            this.deletePlayer.Text = "Supprimer";
            this.deletePlayer.UseVisualStyleBackColor = true;
            // 
            // pseudoField
            // 
            this.pseudoField.Location = new System.Drawing.Point(142, 295);
            this.pseudoField.Name = "pseudoField";
            this.pseudoField.Size = new System.Drawing.Size(120, 20);
            this.pseudoField.TabIndex = 1;
            // 
            // addPlayer
            // 
            this.addPlayer.Location = new System.Drawing.Point(268, 295);
            this.addPlayer.Name = "addPlayer";
            this.addPlayer.Size = new System.Drawing.Size(75, 23);
            this.addPlayer.TabIndex = 2;
            this.addPlayer.Text = "Ajouter";
            this.addPlayer.UseVisualStyleBackColor = true;
            // 
            // refreshPlayer
            // 
            this.refreshPlayer.Location = new System.Drawing.Point(9, 295);
            this.refreshPlayer.Name = "refreshPlayer";
            this.refreshPlayer.Size = new System.Drawing.Size(75, 23);
            this.refreshPlayer.TabIndex = 1;
            this.refreshPlayer.Text = "Actualiser";
            this.refreshPlayer.UseVisualStyleBackColor = true;
            this.refreshPlayer.Click += new System.EventHandler(this.refreshPlayer_Click);
            // 
            // displayPlayers
            // 
            this.displayPlayers.AllowUserToAddRows = false;
            this.displayPlayers.AllowUserToDeleteRows = false;
            this.displayPlayers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.displayPlayers.Location = new System.Drawing.Point(9, 6);
            this.displayPlayers.Name = "displayPlayers";
            this.displayPlayers.ReadOnly = true;
            this.displayPlayers.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.displayPlayers.Size = new System.Drawing.Size(415, 273);
            this.displayPlayers.TabIndex = 0;
            this.displayPlayers.CurrentCellChanged += new System.EventHandler(this.playerSelectedChanged);
            // 
            // scoreTab
            // 
            this.scoreTab.AccessibleName = "scoreTab";
            this.scoreTab.Controls.Add(this.refreshScore);
            this.scoreTab.Controls.Add(this.displayHighScore);
            this.scoreTab.Location = new System.Drawing.Point(4, 22);
            this.scoreTab.Name = "scoreTab";
            this.scoreTab.Padding = new System.Windows.Forms.Padding(3);
            this.scoreTab.Size = new System.Drawing.Size(737, 349);
            this.scoreTab.TabIndex = 1;
            this.scoreTab.Text = "Score";
            this.scoreTab.UseVisualStyleBackColor = true;
            // 
            // refreshScore
            // 
            this.refreshScore.Location = new System.Drawing.Point(9, 262);
            this.refreshScore.Name = "refreshScore";
            this.refreshScore.Size = new System.Drawing.Size(75, 23);
            this.refreshScore.TabIndex = 1;
            this.refreshScore.Text = "Actualiser";
            this.refreshScore.UseVisualStyleBackColor = true;
            this.refreshScore.Click += new System.EventHandler(this.refreshScore_Click);
            // 
            // displayHighScore
            // 
            this.displayHighScore.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.displayHighScore.Location = new System.Drawing.Point(9, 6);
            this.displayHighScore.Name = "displayHighScore";
            this.displayHighScore.Size = new System.Drawing.Size(374, 250);
            this.displayHighScore.TabIndex = 0;
            // 
            // winPartieTab
            // 
            this.winPartieTab.AccessibleName = "winPartieTab";
            this.winPartieTab.Controls.Add(this.partieCountField);
            this.winPartieTab.Controls.Add(this.refreshpartie);
            this.winPartieTab.Controls.Add(this.displayPartieParJoueur);
            this.winPartieTab.Location = new System.Drawing.Point(4, 22);
            this.winPartieTab.Name = "winPartieTab";
            this.winPartieTab.Padding = new System.Windows.Forms.Padding(3);
            this.winPartieTab.Size = new System.Drawing.Size(737, 349);
            this.winPartieTab.TabIndex = 2;
            this.winPartieTab.Text = "Parties gagnées";
            this.winPartieTab.UseVisualStyleBackColor = true;
            // 
            // partieCountField
            // 
            this.partieCountField.AutoSize = true;
            this.partieCountField.Location = new System.Drawing.Point(458, 31);
            this.partieCountField.Name = "partieCountField";
            this.partieCountField.Size = new System.Drawing.Size(63, 13);
            this.partieCountField.TabIndex = 2;
            this.partieCountField.Text = "WAITING...";
            // 
            // refreshpartie
            // 
            this.refreshpartie.Location = new System.Drawing.Point(9, 292);
            this.refreshpartie.Name = "refreshpartie";
            this.refreshpartie.Size = new System.Drawing.Size(75, 23);
            this.refreshpartie.TabIndex = 1;
            this.refreshpartie.Text = "Actualiser";
            this.refreshpartie.UseVisualStyleBackColor = true;
            this.refreshpartie.Click += new System.EventHandler(this.refreshPartie_Click);
            // 
            // displayPartieParJoueur
            // 
            this.displayPartieParJoueur.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.displayPartieParJoueur.Location = new System.Drawing.Point(0, 0);
            this.displayPartieParJoueur.Name = "displayPartieParJoueur";
            this.displayPartieParJoueur.Size = new System.Drawing.Size(452, 273);
            this.displayPartieParJoueur.TabIndex = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(740, 371);
            this.Controls.Add(this.tabControl);
            this.Name = "Form1";
            this.Text = "Service Web";
            loosePartieTab.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.displayLooser)).EndInit();
            this.tabControl.ResumeLayout(false);
            this.playerTab.ResumeLayout(false);
            this.playerTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayPlayers)).EndInit();
            this.scoreTab.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.displayHighScore)).EndInit();
            this.winPartieTab.ResumeLayout(false);
            this.winPartieTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.displayPartieParJoueur)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl;
        private System.Windows.Forms.TabPage playerTab;
        private System.Windows.Forms.TabPage scoreTab;
        private System.Windows.Forms.DataGridView displayPlayers;
        private System.Windows.Forms.Button refreshPlayer;
        private System.Windows.Forms.DataGridView displayHighScore;
        private System.Windows.Forms.Button refreshScore;
        private System.Windows.Forms.Button addPlayer;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox pseudoField;
        private System.Windows.Forms.Button deletePlayer;
        private System.Windows.Forms.TabPage winPartieTab;
        private System.Windows.Forms.Button refreshpartie;
        private System.Windows.Forms.DataGridView displayPartieParJoueur;
        private System.Windows.Forms.Button refreshLoose;
        private System.Windows.Forms.DataGridView displayLooser;
        private System.Windows.Forms.Label partieCountField;
        private System.Windows.Forms.Button updatePlayer;

    }
}

